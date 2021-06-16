const baseUrl = window.location.protocol + '//' + (window.location.href).match('^(?:http:\/\/|www\.|https:\/\/)([^\/]+)')[1];

$('#checkboxSubmit').on('click', function(event) {
    let username = document.querySelector('#username').getAttribute('value');
    username = encodeURIComponent(username);
    let checkedCourse = [];
    let allCourse = $("input[type='checkbox']").toArray();
    allCourse.forEach((item)=> {
        if ($(item).is(":checked")) {
            checkedCourse.push(encodeURIComponent($(item).val()));
        }
    });
    location.href = `${baseUrl}/personalFilter?listMatkul=${checkedCourse}&username=${username}`;
});