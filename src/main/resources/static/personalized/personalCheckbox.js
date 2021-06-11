const baseUrl = window.location.protocol + '//' + (window.location.href).match('^(?:http:\/\/|www\.|https:\/\/)([^\/]+)')[1];

let checkedCourse = [];

$('#checkBoxSubmit').on('click', function(event) {

    let allCourse = $("input[type='checkbox']").toArray();
    console.log(allCourse);
    allCourse.forEach((item)=> {
        if ($(item).is(":checked")) {
            checkedCourse.push($(item).val());
        }
    });
    console.log(checkedCourse);
    location.href = `${baseUrl}/personalFilter?listMatkul=${checkedCourse}`;
});