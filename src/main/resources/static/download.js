let photos = [];

function reload(){
    location.reload();
}

async function downloadPhotos(){
    for(i = 0; i < photos.length; i++){
        await fetch('http://localhost:8080/download/' + photos[i]);
    }
}

document.addEventListener("click", function (e) {
    if(e.target.classList.contains("photo")){

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