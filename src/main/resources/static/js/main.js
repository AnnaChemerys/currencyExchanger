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

$(function() {
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
//            finish: '<button type="submit" class="btn btn-info col-2">Save</button>',
//            finish : '<i class="zmdi zmdi-chevron-right"></i>',
            current : ''
        },
        onStepChanging: function (event, currentIndex, newIndex) {

        if (currentIndex == 0) { //if last step
                   //remove default #finish button
                   $('#form-total').find('a[href="#finish"]').remove();
                   //append a submit type button

                   $('#form-total .actions ul:last-child').append('<button type="submit" class="button"><div class="tick"></div></button>');
//                   $('#form-total .actions ul:last-child').append('<button type="submit" id="submit">Submit</button>');

let button = document.querySelector('.button');
let buttonText = document.querySelector('.tick');

const tickMark = "<svg width=\"58\" height=\"45\" viewBox=\"0 0 58 45\" xmlns=\"http://www.w3.org/2000/svg\"><path fill=\"#fff\" fill-rule=\"nonzero\" d=\"M19.11 44.64L.27 25.81l5.66-5.66 13.18 13.18L52.07.38l5.65 5.65\"/></svg>";

buttonText.innerHTML = "Submit";

button.addEventListener('click', function() {

  if (buttonText.innerHTML !== "Submit") {
    buttonText.innerHTML = "Submit";
  } else if (buttonText.innerHTML === "Submit") {
    buttonText.innerHTML = tickMark;
  }
  this.classList.toggle('button__circle');
});
                }

        console.log(typeof currentIndex);

        console.log(newIndex);

//        if (currentIndex === 1) { //if last step
//                    //remove default #finish button
//                    $('#wizard').find('a[href="#finish"]').remove();
//                    //append a submit type button
//                    $('#wizard .actions li:last-child').append('<button type="submit" id="submit" class="btn-large"><span class="fa fa-chevron-right"></span></button>');
//                    };
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
        },
//        onStepChanged: function (event, currentIndex, priorIndex) {
//        if (currentIndex == 0) { //if last step
//           //remove default #finish button
//           $('#wizard').find('a[href="#finish"]').remove();
//           //append a submit type button
//           $('#wizard .actions li:last-child').append('<button type="submit" id="submit" class="btn-large"><span class="fa fa-chevron-right"></span></button>');
//        }
//        }

//        onFinished: function (event, currentIndex) {
//        $("#form-total").submit();
//        }
    });
});

let button = document.querySelector('.button');
let buttonText = document.querySelector('.tick');

const tickMark = "<svg width=\"18\" height=\"45\" viewBox=\"0 0 58 45\" xmlns=\"http://www.w3.org/2000/svg\"><path fill=\"#fff\" fill-rule=\"nonzero\" d=\"M19.11 44.64L.27 25.81l5.66-5.66 13.18 13.18L52.07.38l5.65 5.65\"/></svg>";

//buttonText.innerHTML = "Submit";

button.addEventListener('click', function() {

//  if (buttonText.innerHTML !== "Submit") {
//    buttonText.innerHTML = "Submit";
//  } else if (buttonText.innerHTML === "Submit") {
//    buttonText.innerHTML = tickMark;
//  }

buttonText.innerHTML = tickMark;
  this.classList.toggle('button__circle');
});
