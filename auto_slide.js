const slides = ['foto.png','foto2.png','foto3.png'];

var Start=0;

function slider(){
    Start=Start+1;
    if(Start>slides.length){
        Start=0;
    } 
}
setInterval(slider,100);


var counter = 1;
setInterval(function(){
    document.getElementById("r" + counter).checked = true;
    counter++;
    if(counter > 3){
        counter = 1;
    }
}, 5000);