(function(dynamite, $, links) {

    function init() {
        if (dynamite.userId()) {
            $(".chzn-select").chosen();
            drawTimeline();
        }
    }

    // Called when the Visualization API is loaded.
    function drawTimeline() {
        // Create and populate a data table.
        var groups = ['CTO', 'Mining', 'Google'],
                hour,
                rows = [],
                options = {width: "100%", height: "99%", style: "box", groupsOnRight: true},
        timeline = new links.Timeline(document.getElementById('timeline')),
                group_gen;

        for (hour = 0; hour < 24; hour++) {
            group_gen = Math.round((Math.random() * 2));

            rows.push({
                start: (new Date(2013, 4, 20, hour, 0, 0)),
                content: 'Article at ' + hour,
                //group: groups[group_gen]
                // Optional: a field 'className'
                // Optional: a field 'editable'
            });
        }

        timeline.draw(rows, options);
    }

    $(document).ready(init);
}(dynamite, jQuery, links));