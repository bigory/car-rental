const car = new Image();
car.src = '/resources/icon/car.png'

car.onclick = function () {
    let start = Date.now();

    let timer = setInterval(function () {
        let timePassed = Date.now() - start;

        car.style.left = timePassed / 5 + 'px';

        if (timePassed > 2000) clearInterval(timer);

    }, 20);
}
