
//Cookie
const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

//DOM Elements
const submitStatusForm = document.getElementById("statusUpdate-form")
const statusUpdateContainer = document.getElementById("statusUpdate-container")

//Modal Elements
let statusBody = document.getElementById('statusUpdate-body')
let updateStatusBtn = document.getElementById('update-statusUpdate-button')

const headers = {
    'Content-Type': 'application/json'
}

const baseUrl = "http://localhost:8080/api/v1/status-updates/"

const handleSubmitStatus = async (e) => {
    e.preventDefault()
    let bodyObj = {
        body: document.getElementById("statusUpdate-input").value
    }
    await updateStatus(bodyObj);
    document.getElementById("statusUpdate-input").value = ''
}

async function updateStatus(obj) {
    const response = await fetch(`${baseUrl}user/${userId}`, {
        method: "POST",
        body: JSON.stringify(obj),
        headers: headers
    })
        .catch(err => console.error(err.message))
    if (response.status == 200) {
        return getStatuses(userId);
    }
}

async function getStatuses(userId) {
    await fetch(`${baseUrl}user/${userId}`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => createStatusCards(data))
        .catch(err => console.error(err))
}

async function handleDeleteStatus(statusId){
    await fetch(`${baseUrl}${statusId}`, {
        method: "DELETE",
        headers: headers
    })
        .catch(err => console.error(err))

    return getStatuses(userId);
}

async function getStatusById(statusId){
    await fetch(`${baseUrl}${statusId}`, {
        method: "GET",
        headers: headers
    })
        .then(res => res.json())
        .then(data => populateStatusModal(data))
        .catch(err => console.error(err.message))
}

async function handleStatusEdit(statusId){
    let bodyObj = {
        id: statusId,
        body: statusBody.value
    }

    await fetch(`${baseUrl}${statusId}`, {
        method: "PUT",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err))

    return getStatuses(userId);
}

const createStatusCards = (array) => {
    statusUpdateContainer.innerHTML = ''
    array.forEach(obj => {
        let statusCard = document.createElement("div")
        statusCard.classList.add("m-2")
        statusCard.innerHTML = `
            <div class="card d-flex" style="width: 18rem; height: 18rem;">
                <div class="card-body d-flex flex-column  justify-content-between" style="height: available">
                    <p class="card-text">${obj.body}</p>
                    <div class="d-flex justify-content-between">
                        <button class="btn btn-danger" onclick="handleDeleteStatus(${obj.id})">Delete</button>
                        <button onclick="getStatusById(${obj.id})" type="button" class="btn btn-primary"
                        data-bs-toggle="modal" data-bs-target="#statusUpdate-edit-modal">
                        Edit
                        </button>
                    </div>
                </div>
            </div>
        `
        statusUpdateContainer.append(statusCard);
    })
}
function handleLogout(){
    let c = document.cookie.split(";");
    for(let i in c){
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}

const populateStatusModal = (obj) =>{
    statusBody.innerText = ''
    statusBody.innerText = obj.body
    updateStatusBtn.setAttribute('data-statusUpdate-id', obj.id)
}

getStatuses(userId);

submitStatusForm.addEventListener("submit", handleSubmitStatus)

updateStatusBtn.addEventListener("click", (e)=>{
    let statusId = e.target.getAttribute('data-statusUpdate-id')
    handleStatusEdit(statusId);
})

