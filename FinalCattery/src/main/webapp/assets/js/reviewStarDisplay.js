$.fn.stars = function () {
    return $(this).each(function () {

        var rating = $(this).data("rating");

        var numStars = $(this).data("numStars");

        var fullStar = new Array(Math.floor(rating + 1)).join('<i class="glyphicon glyphicon-star"></i>');

        var halfStar = ((rating % 1) !== 0) ? '<i class="fa fa-star-half-empty"></i>' : '';

        var noStar = new Array(Math.floor(numStars + 1 - rating)).join('<i class="glyphicon glyphicon-star-empty"></i>');

        $(this).html(fullStar + halfStar + noStar);

    });
};
$('.stars').stars();