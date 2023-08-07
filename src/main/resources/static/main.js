async function uploadFile() {
    let formData = new FormData();
    for(let i = 0; i < fileupload.files.length; i++){
        formData.append("data", fileupload.files[i]);
    }
    await fetch('http://localhost:8080/post', {
        method: "POST",
        body: formData
    })
    reload();
}

function deletePhotoFromModal() {
    const modalImg = document.getElementsByClassName("modal-img");
    const id = modalImg[0].id;
    deletePhoto(id);
}

async function deletePhoto(id){
    await fetch('http://localhost:8080/delete/' + id, { method: "DELETE" })
    .then(res => {
        if(res.ok){
            console.log("ok");
        }else{
            console.log("fehler");
        }
    })
    reload();
}

function downloadPhotoFromModal(e){
    const modalImg = document.getElementsByClassName("modal-img");
    const id = modalImg[0].id;
    downloadPhoto(id);
}

async function downloadPhoto(id){
    await fetch('http://localhost:8080/download/' + id).then(res => console.log(res));
}

document.addEventListener("click", function (e){
    if(e.target.classList.contains("photo")){
        const src = e.target.getAttribute("src");
        const id = e.target.getAttribute("id");
        const element = document.querySelector(".modal-img");
        element.src = src;
        element.id = id;
        const myModal = new bootstrap.Modal(document.getElementById('photo-modal'))
        myModal.show();
    }
})

function showInputChange(input) {
    const size = input.files.length;
    if(size == 1){
        document.getElementById("label").innerHTML  = size + " Element";
    }else{
        document.getElementById("label").innerHTML  = size + " Elemente";
    }
}

function reload(){
    location.reload();
}