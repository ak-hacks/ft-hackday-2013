(function(dynamite, $) {

    function init() {
        $(document).on('dynamitePopularContentReady', function(event, data) {
            var homepage_top5 = $(document.createElement('div'));
            homepage_top5.addClass('dynamite article package');
            homepage_top5.append('<h1><a href="teamDynamite.html">Your peers are reading</a></h1>');
            homepage_top5.append(renderTop5(data));
            $('.master-row.contentSection').prepend(homepage_top5);
        });
    }

    function renderTop5(data) {
        var i, html = [];

        for (i = 0; i < dynamite.areas.length; i++) {
            if (data.hasOwnProperty(dynamite.areas[i].dataName)) {
                if (data[dynamite.areas[i].dataName].length > 0) {
                    html.push('<div class="article first image package">');
                    html.push('<h2>');
                    html.push(dynamite.areas[i].title.replace('**name**', data[dynamite.areas[i].areaName]));
                    html.push('</h2><ul>');
                    html.push(renderArea(data[dynamite.areas[i].dataName].slice(0,5)));
                }
            }
        }

        return html.join('');
    }

    function renderArea(data) {
        var html = [], article;

        for (article in data) {
            if (data.hasOwnProperty(article)) {
                html.push('<li><a href="' + data[article].articleUrl + '">' + data[article].articleTitle + '</a> <span class="date">' + data[article].publishedDate + '</span></li>');
            }
        }

        return html.join('');
    }


    $(document).ready(init);

}(dynamite, jQuery));
