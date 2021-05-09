const baseUrl = window.location.protocol + '//' + (window.location.href).match('^(?:http:\/\/|www\.|https:\/\/)([^\/]+)')[1];

const pickColor = (deadline) => {
	const today = new Date();
	const assignmentDate = new Date(deadline);
	const diff = Math.ceil((assignmentDate - today) / 3600000);
	if (diff < 0) {
		return '#F5F5F5';
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
			return {
				'id': assignment.idAssignment,
				'name': assignment.title,
				'date': assignment.deadline,
				'color': pickColor(assignment.deadline),
				'badge': assignment.angkatan
			}
		}
	);
	$('#calendar').evoCalendar('addCalendarEvent', assignments);
};

// Fetch the data
const fetchData = (year = 0, major = '') => {
	fetch(`${baseUrl}/main?year=${year}&major=${major}`)
	.then(response => response.json())
	.then(responseJson => renderData(responseJson))
	.catch(error => {
	    alert('There was an error when fetching the data, please try again later!');
	})
};

$(document).ready(function() {
	$('#calendar').evoCalendar({
		'sidebarDisplayDefault': false
	});
	fetchData();
});

$('#calendar').on('selectDate', function(event, newDate, oldDate) {
	$('#calendar').evoCalendar('toggleEventList', true);
});

$('#calendar').on('selectEvent', function(event, activeEvent) {
	// Will be used to redirect to details page
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
	fetchData(year, major);
});
