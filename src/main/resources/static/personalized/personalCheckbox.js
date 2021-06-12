const baseUrl = window.location.protocol + '//' + (window.location.href).match('^(?:http:\/\/|www\.|https:\/\/)([^\/]+)')[1];

$('#checkBoxSubmit').on('click', function(event) {
    let checkedCourse = [];
    let allCourse = $("input[type='checkbox']").toArray();
    allCourse.forEach((item)=> {
        if ($(item).is(":checked")) {
            checkedCourse.push(encodeURIComponent($(item).val()));
        }
    });
    location.href = `${baseUrl}/personalFilter?listMatkul=${checkedCourse}`;
});