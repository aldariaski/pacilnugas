// The data/time we want to countdown to
var dateTime = dates + ' ' + time;
var countDownDate = new Date(dateTime).getTime();
console.log(dateTime);
// var countDownDate = new Date("May 30 2021 22:04:30").getTime();


// Run myfunc every second
var myfunc = setInterval(function() {

    var longDate = new Date(dates);
    var formattedDate = longDate.toDateString();
    console.log(formattedDate);
    document.getElementById("dateFormatted").innerHTML = formattedDate;

    var now = new Date().getTime();
    var timeleft = countDownDate - now;

    // Calculating the days, hours, minutes and seconds left
    var days = Math.floor(timeleft / (1000 * 60 * 60 * 24));
    var hours = Math.floor((timeleft % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
    var minutes = Math.floor((timeleft % (1000 * 60 * 60)) / (1000 * 60));
    var seconds = Math.floor((timeleft % (1000 * 60)) / 1000);

    // Result is output to the specific element
    document.getElementById("days").innerHTML = ('0' + days).slice(-2);
    document.getElementById("hours").innerHTML = ('0' + hours).slice(-2);
    document.getElementById("mins").innerHTML = ('0' + minutes).slice(-2);
    document.getElementById("secs").innerHTML = ('0' + seconds).slice(-2);

    // Display the message when countdown is over
    if (timeleft < 0) {
        clearInterval(myfunc);
        document.getElementById("days").innerHTML = "00"
        document.getElementById("hours").innerHTML = "00"
        document.getElementById("mins").innerHTML = "00"
        document.getElementById("secs").innerHTML = "00"
        document.getElementById("end1").innerHTML = "OVERDUED";
    }
    else if (timeleft < 86400000 && timeleft > 0) {
        document.getElementById("end2").innerHTML = "LESS THAN A DAY";
    }
    else if (timeleft > 86400000) {
        document.getElementById("end3").innerHTML = "ON TIME";
    }

}, 1000);
