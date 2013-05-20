$.fn.serializeObject = function() {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

var dynamite = (function($) {

    var areas = [
            {dataName: 'byPosition', areaName: 'positionName', title: 'Articles read by other **name**s.'},
            {dataName: 'bySector', areaName: 'sectorName', title: 'Articles read by people in the **name** sector.'},
            {dataName: 'byCompany', areaName: 'companyName', title: 'Articles read by people in your company, **name**.'},
        ],
        popularArticlesUrl = 'rest/recommend/user/'/*,'/jsonSamples/recommendationsForUser.json?user='*/;


    function userId() {
        var user = document.cookie.match(/dynamiteUser=([^;]+)/);
        if (user) {
            return user[1];
        }

        return false;
    }

    function signInBox() {
        $('#signin-click').click(function() {
            $('#signin-box').toggle();
        });

        $('#signin-thinker,#signin-functional,#signin-student').click(function() {
            switch ($(this).attr('id')) {
                case 'signin-thinker':
                    document.cookie = "dynamiteUser=12345";
                    break;
                case 'signin-functional':
                    document.cookie = "dynamiteUser=456";
                    break;
                case 'signin-student':
                    document.cookie = "dynamiteUser=789";
                    break;
            }

            document.location.reload(true);
        });
    }

    function getPopularArticles() {
        $.ajax({
            url: popularArticlesUrl + userId(),
            dataType: 'json',
            success: function(json) {
                $(document).trigger('dynamitePopularContentReady', json.recommendationForUser);
            }
        });
    }

    function createTab(event, data) {
        var dynamite_tab = $(document.createElement('div')).attr('id', 'dynamite_tag');

        dynamite_tab.css('right', '-250px').html([
            '<h1><a href="teamDynamite.html">People like you, also read</a></h1>',
            '<p><span id="article_flasher" style="display:none"></span></p>'
        ].join(''));

        $('body').prepend(dynamite_tab);

        dynamite_tab.animate({
            right: '-1em'
        }, {complete: function() {
            rollTabArticles(data)
        }});
    }

    function rollTabArticles(data) {
        var articles = [], i;

        for (i = 0; i < areas.length; i++) {
            if (data.hasOwnProperty(areas[i].dataName)) {
                if (data[areas[i].dataName].length > 0) {
                    articles = articles.concat(data[areas[i].dataName]);
                }
            }
        }

        flashArticle(articles, 0);
    }

    function flashArticle(articles, index) {
        if (index >= articles.length) {
            index = 0;
        }

        $('#article_flasher')
            .html('<a href="' + articles[index].articleUrl + '">' + articles[index].articleTitle + '</a>')
            .fadeIn()
            .delay(3000)
            .fadeOut(1000, function() {
                flashArticle(articles, (index + 1));
            });
    }

    function init() {
        if (userId()) {
            if (document.location.pathname.indexOf('teamDynamite.html') === -1){
                $(document).on('dynamitePopularContentReady', createTab);
            }
            getPopularArticles();
        }
        signInBox();
    }

    $(document).ready(init);

    return {
        areas: areas,
        userId: userId
    };

}(jQuery));