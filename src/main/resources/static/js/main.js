//$(function() {
//
//  $('.js-check-all').on('click', function() {
//
//  	if ( $(this).prop('checked') ) {
//	  	$('th input[type="checkbox"]').each(function() {
//	  		$(this).prop('checked', true);
//	  	})
//  	} else {
//  		$('th input[type="checkbox"]').each(function() {
//	  		$(this).prop('checked', false);
//	  	})
//  	}
//  });
//
//
//
//});

$(function(){
    $("#form-total").steps({
        headerTag: "h2",
        bodyTag: "section",
        transitionEffect: "fade",
        enableAllSteps: true,
        autoFocus: true,
        transitionEffectSpeed: 500,
        titleTemplate : '<div class="title">#title#</div>',
        labels: {
            previous : 'Back',
            next : '<i class="zmdi zmdi-chevron-right"></i>',
            finish : '<i class="zmdi zmdi-chevron-right"></i>',
            current : ''
        },
        onStepChanging: function (event, currentIndex, newIndex) {
            var fullname = $('#first_name').val() + ' ' + $('#last_name').val();
            var sell_sum = $('#sell_sum').val();
            var sell_currency_code = $('#sell_currency_code').val();
            var receive_sum = $('#receive_sum').val();
            var receive_currency_code = $('#receive_currency_code').val();
//            var account_number = $('#account_number').val();

            $('#fullname-val').text(fullname);
            $('#sell_sum-val').text(sell_sum);
            $('#sell_currency_code-val').text(sell_currency_code);
            $('#receive_sum-val').text(receive_sum);
            $('#receive_currency_code-val').text(receive_currency_code);
//            $('#account-name-val').text(account_name);
//            $('#account-number-val').text(account_number);

            return true;
        }
    });
});
