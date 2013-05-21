(function(dynamite, $, links) {

    var data_url = 'rest/recommend/query/';

    function init() {
        if (dynamite.userId()) {
            $(".chzn-select").chosen();
            $(document).on('dynamitePopularContentReady', selectValues);
            selectListeners();
            drawTimeline({});
        }
    }

    // Called when the Visualization API is loaded.
    function drawTimeline(data) {
        selectValues(null, data);

        // Create and populate a data table.
        var rows = [],i, a, article, articles = [], options = {width: "100%", height: "99%", style: "box", groupsOnRight: true},
            timeline = new links.Timeline(document.getElementById('timeline'));

        for (i = 0; i < dynamite.areas.length; i++) {
            if (data.hasOwnProperty(dynamite.areas[i].dataName)) {
                if (data[dynamite.areas[i].dataName].length > 0) {
                    articles = articles.concat(data[dynamite.areas[i].dataName].slice(0,10));
                }
            }
        }

        for (a in articles) {
            if (articles.hasOwnProperty(a)) {
                article = articles[a];

                rows.push({
                    start: (new Date(Date.parse(article.publishedDate))),
                    content: '<a href="'+article.articleUrl+'">'+article.articleTitle+'</a>',
                    className: 'ft-timeline'
                    //group: groups[group_gen]
                    // Optional: a field 'editable'
                });
            }
        }

        timeline.draw(rows, options);
    }

    function selectValues(event, data) {
        var defaults = {
            time: 'Last hour',
            companyName: '',
            positionName: '',
            sectorName: ''
        };

        $('form#filters select[name=time]').val(defaults.time).trigger("liszt:updated");
        $('form#filters select[name=companyName]').val(data.companyName || defaults.companyName).trigger("liszt:updated");
        $('form#filters select[name=positionName]').val(data.positionName || defaults.positionName).trigger("liszt:updated");
        $('form#filters select[name=sectorName]').val(data.sectorName || defaults.sectorName).trigger("liszt:updated");
        $('form#filters select[name=sectorName]').change();

        if(data.companyName || data.positionName || data.sectorName) {
            var title = [];

            if(data.sectorName) {
                title.push(data.sectorName);
            }

            if(data.positionName) {
                title.push(data.positionName+'s');
            }
            else {
                title.push('People');
            }

            title.push('in')

            if(data.companyName) {
                title.push(data.companyName);
            }

            $('#dynamite_title').text(title.join(' ') + '.');
        }

    }

    function selectListeners() {
        $(".chzn-select").on('change', function() {
            $.ajax({
                url: data_url,
                dataType: 'json',
                contentType: 'application/json',
                data: JSON.stringify($('#filters').serializeObject()),
                type: 'POST',
                success: function(data) { drawTimeline(data.recommendationsForUser); }
            })
        });
    }

    $(document).ready(init);
}(dynamite, jQuery, links));