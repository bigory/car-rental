(function(){
    let car = document.getElementById("car");
    if (car instanceof HTMLElement && car.parentNode != null) {
        car.addEventListener("click" , (e) =>  {
            e.preventDefault();
            car.classList.add("start");
        });
        car.addEventListener("transitionend", (e) => {
            if (car.classList.contains("start") && !car.classList.contains("finish")) {
                car.classList.add("finish");
                car.classList.add("rotate")
            } else {
                car.classList.remove("start");
                car.classList.remove("rotate");
                car.classList.remove("finish");
            }
        })
    }
})();
