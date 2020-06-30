$(function() {
	$.datepicker.setDefaults({
		numberOfMonths : 1
	});
	$('#introduced').datepicker(
			{
				onSelect : function(dateStr) {
					$('#discontinued').datepicker('option', 'minDate',
							$(this).datepicker('getDate'));
				}
			});
	$('#discontinued').datepicker(
			{
				onSelect : function(dateStr) {

					$('#introduced').datepicker('option', 'maxDate',
							$(this).datepicker('getDate'));

				}
			});
});