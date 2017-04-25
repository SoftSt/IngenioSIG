/* 
 * (c) 2017 NewVi Cia. Ltda.
 *   * 
 */

PrimeFaces.widget.VisorGeografico = PrimeFaces.widget.BaseWidget.extend({
    setLayersByCollection: function (layerCollection) {
        var layers = [];
        for (var i = 0; i < layerCollection.length; i++) {
            layers.push(this.getLayerByType(layerCollection[i]));
        }
        ;
        this.layers = layers;
    },
    getLayers: function () {
        return this.layers;
    },
    getLayerByType: function (layerObject) {
        var layer;
        var options = {};
        options.opacity = layerObject.opacity;
        options.visible = layerObject.visible;
        if (layerObject.type === "Tile") {
            options.source = this.getSourceByType(layerObject.source);
            layer = new ol.layer.Tile(options);
        }
        return layer;
    },
    getSourceByType: function (sourceObject) {
        var source;
        if (sourceObject.type === "OSM") {
            source = new ol.source.OSM();
        }
        if (sourceObject.type === "BingMaps") {
            source = new ol.source.BingMaps({
                key: sourceObject.key,
                imagerySet: sourceObject.imagerySet
            });
        }
        if (sourceObject.type === "TileWMS") {
            source = new ol.source.TileWMS({
                url: sourceObject.url,
                params: sourceObject.params,
                serverType: sourceObject.serverType
            });
        }
        return source;
    },
    setView: function (centro, srs, zoom) {
        var posCentro = ol.proj.transform(centro, srs, 'EPSG:3857');
        this.view = new ol.View({
            center: posCentro,
            zoom: zoom
        });
    },
    getView: function () {
        return this.view;
    },
    actualizarVistaMapa: function (vista) {
        this.mapa.setView(vista);
    },
    init: function (cfg) {
        this.cfg = cfg;
        this._super(this.cfg);
        // Generar mapa
        if (this.cfg.map) {
            var mapa = JSON.parse(this.cfg.map.replace(/'/g, "\""));
            this.setView(mapa.view.center, mapa.view.projection, mapa.view.zoom);
            this.setLayersByCollection(mapa.layers);
        } else {
            this.setView([0, 0], 'EPSG:3857', 1);
        }
        // Colocar un tamaño (ancho y alto)
        this.colorScheme = this.cfg.colorScheme || 'standard';
        this.interval = setInterval((function (self) {
            return function () {
                self.update();
            };
        })(this), 1000);
        this.draw();
    },
    draw: function () {
        // Inicializar el componente de mapa
        this.mapa = new ol.Map({
            target: this.cfg.id,
            layers: this.getLayers(),
            view: this.getView()
        });
        // Inicializar
        this.update();
    },

    update: function () {
    }


});