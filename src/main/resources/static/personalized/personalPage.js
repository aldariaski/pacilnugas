const baseUrl = window.location.protocol + '//' + (window.location.href).match('^(?:http:\/\/|www\.|https:\/\/)([^\/]+)')[1];

$('#checkboxSubmit').on('click', function(event) {
    let username = document.querySelector('#username').getAttribute('value');
    let checkedCourse = [];
    let allCourse = $("input[type='checkbox']").toArray();
    allCourse.forEach((item)=> {
        if ($(item).is(":checked")) {
            checkedCourse.push(encodeURIComponent($(item).val()));
        }
    });
    console.log(username);
    location.href = `${baseUrl}/personalFilter?listMatkul=${checkedCourse}&username=${username}`;
});