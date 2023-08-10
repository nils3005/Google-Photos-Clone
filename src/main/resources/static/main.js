let isSelect = true;
let photos = [];

/*
async function uploadFile(){
    let formData = new FormData();
    for(let i = 0; i < fileupload.files.length; i++){
        formData.append("data", fileupload.files[i]);
    }
    await fetch('http://localhost:8080/post', {
            method: "POST",
            body: formData
            });
    reload();
}
*/

async function uploadFile(){
    const files = fileupload.files;
    for(let i = 0; i < files.length; i++){
        const d = new Date(files[i].lastModified);
        const date = d.getDate() + "-" + (d.getMonth()+1) + "-" + d.getFullYear();
        console.log(date);

        const name = files[i].name;
        console.log(name);

        const type = files[i].type;
        console.log(type);

        const base64 = await toBase64(files[i]);
        console.log(base64);

        fetch("http://localhost:8080/post", {
            method: "POST",
            headers: {
                "Content-Type":"application/json"
            },
            body: JSON.stringify({
                "fileName" : name,
                "contentType" : type,
                "base64" : base64,
                "lastmodified" : date
            })
        });
    }
    reload();
}

const toBase64 = file => new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => resolve(reader.result);
    reader.onerror = reject;
});

async function deleteFile(id){
    await fetch('http://localhost:8080/delete/' + id, { method: "DELETE" });
    reload();
}

async function downloadFile(id){ window.location.href= ("http://localhost:8080/download/" + id); }

function deletePhotoFromModal() {
    const modalImg = document.getElementsByClassName("modal-img");
    const id = modalImg[0].id;
    deleteFile(id);
}

function downloadPhotoFromModal(e){
    const modalImg = document.getElementsByClassName("modal-img");
    const id = modalImg[0].id;
    downloadFile(id);
}

document.addEventListener("click", function (e){
    if(isSelect && e.target.classList.contains("photo")){
        const src = e.target.getAttribute("src");
        const id = e.target.getAttribute("id");
        const element = document.querySelector(".modal-img");
        element.src = src;
        element.id = id;
        const myModal = new bootstrap.Modal(document.getElementById('photo-modal'))
        myModal.show();
    }
    if(!isSelect && e.target.classList.contains("photo")){
        const element = e.target.parentElement;
        if(photos.includes(e.target.id)){
            element.classList.remove("border");
            element.classList.remove("border-primary");
            const index = photos.indexOf(e.target.id);
            photos.splice(index, 1);
        } else {
            element.classList.add("border");
            element.classList.add("border-primary");
            photos.push(e.target.id);
        }
    }
})

function deleteSelectedPhotos(e){
    if(!e.classList.contains("disabled")){
        for(let i = 0; i < photos.length; i++){
            deleteFile(photos[i]);
        }
        photos = [];
    }
}

function downloadSelectedPhotos(e){
    if(!e.classList.contains("disabled")){
        for(let i = 0; i < photos.length; i++){
            downloadFile(photos[i]);
        }
        photos = [];
    }
}

function toggleSelect(e){
    if(isSelect){
        document.getElementById("multipleDelete").classList.remove("disabled");
        document.getElementById("multipleDownload").classList.remove("disabled");
        e.classList.remove("bi-toggle-off");
        e.classList.add("bi-toggle-on");
        isSelect = false;
    }else{
        document.getElementById("multipleDelete").classList.add("disabled");
        document.getElementById("multipleDownload").classList.add("disabled");
        e.classList.add("bi-toggle-off");
        e.classList.remove("bi-toggle-on");

        const elements = document.querySelectorAll("div.form-check");
        for(let i = 0; i < elements.length; i++){
            if(elements[i].classList.contains("border")){
                elements[i].classList.remove("border");
                elements[i].classList.remove("border-primary");
                console.log(elements[i].id);
            }
        }
        photos = [];
        isSelect = true;
    }
}

function showInputChange(input) {
    const size = input.files.length;
    if(size == 1){
        document.getElementById("label").innerHTML  = size + " Element";
    }else{
        document.getElementById("label").innerHTML  = size + " Elemente";
    }
}

function changeView(e){
    switch(e.id){
        case "3cols":
            if(!e.classList.contains("disabled")){
                const divs = document.querySelectorAll("div.col-sm-6");
                for(let i = 0; i < divs.length; i++){
                    divs[i].classList.remove("col-sm-6");
                    divs[i].classList.add("col-sm-4");
                }
                e.classList.add("disabled");
                document.getElementById("2cols").classList.remove("disabled");
            }
            break;
        case "2cols":
            if(!e.classList.contains("disabled")){
               const divs = document.querySelectorAll("div.col-sm-4");
               for(let j = 0; j < divs.length; j++){
                    divs[j].classList.remove("col-sm-4");
                    divs[j].classList.add("col-sm-6");
               }
               e.classList.add("disabled");
               document.getElementById("3cols").classList.remove("disabled");
            }
            break;
    }
}

function reload(){ location.reload(); }