var map, drawControls;

function init() {
    map = new OpenLayers.Map('map');

    var wmsLayer = new OpenLayers.Layer.WMS("OpenLayers WMS", "http://vmap0.tiles.osgeo.org/wms/vmap0?", {
        layers: 'basic'
    });

    var vector = new OpenLayers.Layer.Vector("Vector Layer", {
        renderers: ['SVGExtended', 'VMLExtended', 'CanvasExtended'],
        styleMap: new OpenLayers.StyleMap({
            'default': OpenLayers.Util.extend({
                orientation: true
            }, OpenLayers.Feature.Vector.style['default']),
            'temporary': OpenLayers.Util.extend({
                orientation: true
            }, OpenLayers.Feature.Vector.style['temporary'])
        })
    });

    map.addLayers([wmsLayer, vector]);
    map.addControl(new OpenLayers.Control.LayerSwitcher());
    map.addControl(new OpenLayers.Control.MousePosition());

    drawControls = {
        line: new OpenLayers.Control.DrawFeature(
            vector,
            OpenLayers.Handler.Path
        ),
        modify: new OpenLayers.Control.ModifyFeature(vector)
    };

    for (var key in drawControls) {
        map.addControl(drawControls[key]);
    }

    map.setCenter(new OpenLayers.LonLat(0, 0), 3);

    document.getElementById('noneToggle').checked = true;
}

function toggleControl(element) {
    for (key in drawControls) {
        var control = drawControls[key];
        if (element.value == key && element.checked) {
            control.activate();
        } else {
            control.deactivate();
        }
    }
}