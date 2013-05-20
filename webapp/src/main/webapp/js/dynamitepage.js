(function(dynamite, $, links) {

    var data_url = 'rest/recommend/query/';

    function init() {
        if (dynamite.userId()) {
            $(".chzn-select").chosen();
            selectListeners();
            drawTimeline({});
        }
    }

    // Called when the Visualization API is loaded.
    function drawTimeline(data) {
        // Create and populate a data table.
        var rows = [],i, a, article, articles = [], options = {width: "100%", height: "99%", style: "box", groupsOnRight: true},
            timeline = new links.Timeline(document.getElementById('timeline'));

        for (i = 0; i < dynamite.areas.length; i++) {
            if (data.hasOwnProperty(dynamite.areas[i].dataName)) {
                if (data[dynamite.areas[i].dataName].length > 0) {
                    articles = articles.concat(data[dynamite.areas[i].dataName]);
                }
            }
        }

        console.log(articles);

        for (a in articles) {
            if (articles.hasOwnProperty(a)) {
                article = articles[a];

                rows.push({
                    start: (new Date(Date.parse(article.publishedDate))),
                    content: '<a href="'+article.articleUrl+'">'+article.articleTitle+'</a>'
                    //group: groups[group_gen]
                    // Optional: a field 'className'
                    // Optional: a field 'editable'
                });
            }
        }

        console.log(rows);

        timeline.draw(rows, options);
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