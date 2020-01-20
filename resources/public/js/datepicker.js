$(function () {
  $('#date').datepicker({
  });

  $('#home').click(function() {
    $('#home').addClass('active');
    $('#games').removeClass('active');
    $('#record').removeClass('active');
  });

  $('#games').click(function() {
    $('#games').addClass('active');
    $('#home').removeClass('active');
    $('#record').removeClass('active');
  });

  $('#record').click(function() {
    $('#record').addClass('active');
    $('#home').removeClass('active');
    $('#games').removeClass('active');
  });
});
