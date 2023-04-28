//const baseUrl = "http://localhost:8080/api/v1/vaccinations";
//
//const headers = {
//  "Content-Type": "application/json",
//};
//
//async function getAllVaccinationsByUserId(userId) {
//  const response = await fetch(`${baseUrl}/user/${userId}`, {
//    method: "GET",
//    headers: headers,
//  });
//
//  if (response.ok) {
//    const vaccinations = await response.json();
//    return vaccinations;
//  } else {
//    throw new Error("Failed to fetch vaccinations");
//  }
//}
//
//async function addVaccination(vaccination) {
//  const response = await fetch(baseUrl, {
//    method: "POST",
//    headers: headers,
//    body: JSON.stringify(vaccination),
//  });
//
//  if (response.ok) {
//    const newVaccination = await response.json();
//    return newVaccination;
//  } else {
//    throw new Error("Failed to add vaccination");
//  }
//}
//
//async function getVaccinationById(vaccinationId) {
//  try {
//    const response = await fetch(`${baseUrl}/${vaccinationId}`);
//    if (!response.ok) {
//      throw new Error('Unable to get vaccination');
//    }
//    const data = await response.json();
//    return data;
//  } catch (error) {
//    console.error(error);
//    return null;
//  }
//}
//
//async function editVaccination(vaccinationId, updates) {
//  const response = await fetch(`${baseUrl}/${vaccinationId}`, {
//    method: "PUT",
//    headers: headers,
//    body: JSON.stringify(updates),
//  });
//
//  if (response.ok) {
//    const updatedVaccination = await response.json();
//    return updatedVaccination;
//  } else {
//    throw new Error("Failed to update vaccination");
//  }
//}
//
//async function deleteVaccination(vaccinationId) {
//  const response = await fetch(`${baseUrl}/${vaccinationId}`, {
//    method: "DELETE",
//    headers: headers,
//  });
//
//  if (!response.ok) {
//    throw new Error("Failed to delete vaccination");
//  }
//}
