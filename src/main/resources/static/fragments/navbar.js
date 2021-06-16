$('#navbar-toggler').on('click', function (event) {
    event.stopPropagation();
    if ($('#navbar').css('left') === '0px') {
        $('#navbar').css('left', '-59px');
        $('#navbar-toggler').css('left', '0px');
    } else {
        $('#navbar').css('left', 0);
        $('#navbar-toggler').css('left', '59px');
    }
});

$('body').on('click', function (event) {
    event.stopPropagation();
    if ($('#navbar-toggler').css('left') === '59px') {
        $('#navbar').css('left', '-59px');
        $('#navbar-toggler').css('left', '0px');
    }
});