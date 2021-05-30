const baseUrl = window.location.protocol + '//' + (window.location.href).match('^(?:http:\/\/|www\.|https:\/\/)([^\/]+)')[1];

let idAssignments = [];

const pickColor = (deadline) => {
	const today = new Date();
	const assignmentDate = new Date(deadline);
	const diff = Math.ceil((assignmentDate - today) / 3600000);
	if (diff < 0) {
		return '#3B3B3B';
	} else if (diff < 24) {
		return '#CE3030';
	} else if (diff < 168) {
		return '#F3AF00';
	} else {
		return '#406AD8';
	}
};

const renderData = (result) => {
	let assignments = result.map(
		assignment => {
			idAssignments.push(assignment.id_activity);
			return {
				'id': assignment.id_activity,
				'name': assignment.title,
				'date': assignment.deadline,
				'color': pickColor(assignment.deadline),
				'badge': assignment.angkatan
			}
		}
	);
	$('#calendar').evoCalendar('addCalendarEvent', assignments);
};

const removeEvent = () => {
	$('#calendar').evoCalendar('removeCalendarEvent', idAssignments);
	idAssignments = [];
}

// Fetch the data
const fetchData = (year = 0, major = '') => {
	$.ajax({
		method: 'GET',
		url: `${baseUrl}/main?year=${year}&major=${major}`,
		dataType: 'json',
		success: function (response) {
			console.log(response);
			renderData(response);
		},
		failed: function (error) {
			alert('There was an error when fetching the data, please try again later!');
		}
	});
};

const main = (year = 0, major = '') => {
	removeEvent();
	$('#calendar').evoCalendar('selectMonth', ((new Date()).getMonth() - 1));
	$('#calendar').evoCalendar('selectMonth', (new Date()).getMonth());
	fetchData(year, major);
}

$(document).ready(function() {
	$('#calendar').evoCalendar({
		'sidebarDisplayDefault': false
	});
	main();
});

$('#calendar').on('selectDate', function(event, newDate, oldDate) {
	$('#calendar').evoCalendar('toggleEventList', true);
});

$('#calendar').on('selectEvent', function(event, activeEvent) {
	location.href = `/task/view/${activeEvent.id}`;
});

// Change the active state of filter year
$('.filter-button.filter-year').on('click', function(event) {
	if ($(this).hasClass('active')) {
		$(this).removeClass('active');
	} else {
		$('.filter-year').removeClass('active');
		$(this).addClass('active');
	}
});

// Change the active state of filter major
$('.filter-button.filter-major').on('click', function(event) {
	if ($(this).hasClass('active')) {
		$(this).removeClass('active');
	} else {
		$('.filter-major').removeClass('active');
		$(this).addClass('active');
	}
});

// Get all the selected filter
$('#filter-submit').on('click', function(event) {
	let year = 0;
	let major = '';
	$('.filter-button.active').each(function() {
		if (Number.isNaN(parseInt($(this).text()))) {
			major = $(this).text();
		} else {
			year = parseInt($(this).text());
		}
	});
	main(year, major);
});
