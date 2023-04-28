
//Cookie
const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

//DOM Elements
const submitAppointmentForm = document.getElementById("appointments-form")
const appointmentContainer = document.getElementById("appointments-container")

//Modal Elements
const updateAppointmentBtn = document.getElementById("update-appointment-button")
const appointmentType = document.getElementById("edit-appointment-type")
const appointmentDate = document.getElementById("edit-appointment-date")
const appointmentTime = document.getElementById("edit-appointment-time")

const headers = {
    "Content-Type": "application/json",
};
const baseUrl = "http://localhost:8080/api/v1/appointments";

let appointmentObj = {
    id: '',
    typeOfAppointment: '',
    appointmentDate: '',
    appointmentTime: ''
};

const handleSubmitAppointment = async (e) => {
    e.preventDefault()

    console.log('Before bodyObj assignment');
      const bodyObj = {
        type: document.getElementById("appointment-type").value,
        date: document.getElementById("appointment-date").value,
        time: document.getElementById("appointment-time").value,
        userId: userId
        };

await createAppointment(bodyObj);

document.getElementById("appointment-type").value = '';
document.getElementById("appointment-date").value = '';
document.getElementById("appointment-time").value = '';
};

async function createAppointment(appointmentObj) {
console.log('appointmentObj:', appointmentObj);
const response = await fetch(`${baseUrl}/users/${userId}`, {
    method: "POST",
    body: JSON.stringify(appointmentObj),
    headers: headers
});
    if (response.ok) {
    return getAllAppointmentsByUserId(userId);
}else {
    throw new Error("Failed to create appointment.");
    }
}

async function getAllAppointmentsByUserId(userId) {
    await fetch(`${baseUrl}/users/${userId}`, {
    method: "GET",
    headers: headers
})
    .then(response => response.json())
    .then(data => createAppointmentCards(data))
    .catch(err => console.error(err))
}

async function handleDeleteAppointment(appointmentId){
    await fetch(`${baseUrl}/${appointmentId}`, {
    method: "DELETE",
    headers: headers
})
    .catch(err => console.error(err))

    return getAllAppointmentsByUserId(userId);

}

async function getAppointmentById(appointmentId){
    await fetch(`${baseUrl}/${appointmentId}`, {
    method: "GET",
    headers: headers
})
    .then(res => res.json())
    .then(data => populateAppointmentModal(data))
    .catch(err => console.error(err.message))
}

async function handleAppointmentEdit(appointmentId){
  let bodyObj = {
    id: appointmentId,
    type: appointmentType.value,
    date: appointmentDate.value,
    time: appointmentTime.value
}
await fetch(`${baseUrl}/${appointmentId}`, {
    method: "PUT",
    body: JSON.stringify(bodyObj),
    headers: headers
})
    .catch(err => console.error(err))

    return getAllAppointmentsByUserId(userId);
}
const createAppointmentCards = (appointments) => {
  appointmentContainer.innerHTML = "";

  appointments.forEach((appointment) => {
    const card = document.createElement("div");
    card.classList.add("card", "m-2");
    card.innerHTML = `
      <div class="card-body">
        <h5 class="card-title">${appointment.title}</h5>
        <p class="card-text">${appointment.description}</p>
        <p class="card-text">${new Date(appointment.date).toLocaleString()}</p>
        <div class="d-flex justify-content-between">
          <button class="btn btn-danger" onclick="handleDeleteAppointment(${appointment.id})">Delete</button>
          <button onclick="getAppointmentById(${appointment.id})" type="button" class="btn btn-primary"
            data-bs-toggle="modal" data-bs-target="#appointment-edit-modal">
            Edit
          </button>
        </div>
      </div>
    `;
    appointmentContainer.append(card);
  });
};

const populateAppointmentModal = (appointmentObj) =>{
    appointmentTitle.innerText = ''
    appointmentTitle.innerText = appointmentObj.title
    appointmentDate.innerText = ''
    appointmentDate.innerText = appointmentObj.date
    appointmentTime.innerText = ''
    appointmentTime.innerText = appointmentObj.time
    appointmentLocation.innerText = ''
    appointmentLocation.innerText = appointmentObj.location
    appointmentNotes.innerText = ''
    appointmentNotes.innerText = appointmentObj.notes
    updateAppointmentBtn.setAttribute('data-appointment-id', appointmentObj.id)
}

getAllAppointmentsByUserId(userId);


submitAppointmentForm.addEventListener("submit", handleSubmitAppointment)

updateAppointmentBtn.addEventListener("click", async (e) => {
  const appointmentId = e.target.getAttribute("data-appointment-id");
  const updates = {
    type: document.getElementById("edit-appointment-type").value,
    date: document.getElementById("edit-appointment-date").value,
    time: document.getElementById("edit-appointment-time").value,
  };
  await editAppointment(appointmentId, updates);
})