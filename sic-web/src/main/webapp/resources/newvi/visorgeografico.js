/* 
 * (c) 2017 NewVi Cia. Ltda.
 *   * 
 */

PrimeFaces.widget.VisorGeografico = PrimeFaces.widget.BaseWidget.extend({
    setVista: function (centro, srs, zoom) {
        var posCentro = ol.proj.transform(centro, srs, 'EPSG:3857');
        this.vista = new ol.View({
            center: posCentro,
            zoom: zoom
        });
    },
    getVista: function() {
        return this.vista;
    },
    actualizarVistaMapa: function (vista) {
        this.mapa.setView(vista);
    },
    init: function (cfg) {
        this.cfg = cfg;
        this._super(this.cfg);
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
        // Generar mapa
        // Inicializar el componente de mapa
        this.mapa = new ol.Map({
            target: this.cfg.id
        });
        // Agregar un mapa base
        var capaOsm = new ol.layer.Tile({
            source: new ol.source.OSM()
        });
        this.mapa.addLayer(capaOsm);
        // Colocar la vista
        if (this.cfg.map) {
            var mapa = JSON.parse(this.cfg.map.replace(/'/g, "\""));
            this.setVista(mapa.view.center, mapa.view.projection, mapa.view.zoom);
        } else {
            this.setVista([0, 0], 'EPSG:3857', 1);
        }
        this.actualizarVistaMapa(this.getVista());
        // Inicializar
        this.update();
    },

    update: function () {
    }


});