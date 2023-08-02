function reloadPhotos(){
            location.reload();
        }

async function uploadFile() {
    let formData = new FormData();
    for(let i = 0; i < fileupload.files.length; i++){
        formData.append("data", fileupload.files[i]);
    }
    await fetch('http://localhost:8080', {
        method: "POST",
        body: formData
    })
    reloadPhotos();
}

function showInputChange(input) {
    const size = input.files.length;
    document.getElementById("label").innerHTML  = size + " Element(e)";
}

async function deleteFile() {
    reloadPhotos();
}

document.addEventListener("click", function (e){
    if(e.target.classList.contains("photo")){
        const src = e.target.getAttribute("src");
        document.querySelector(".modal-img").src = src;
        const myModal = new bootstrap.Modal(document.getElementById('photo-modal'))
        myModal.show();
    }
})