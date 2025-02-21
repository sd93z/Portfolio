const openMenu = () =>{
    const menu = document.querySelector(".header-menu");
    menu.classList.toggle("active");
    if(menu.classList.contains("active")){
        document.querySelector("header .material-icons").innerHTML = "close";

    }
    else{
        document.querySelector("header .materials-icons").innerHTML = "menu"
    }
}

document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener('click', function (e) {
        e.preventDefault();
        const target = document.querySelector(this.getAttribute('href'));
        if (target) {
            target.scrollIntoView({
                behavior: 'smooth',
                block: 'start'
            });
        }
    });
});
