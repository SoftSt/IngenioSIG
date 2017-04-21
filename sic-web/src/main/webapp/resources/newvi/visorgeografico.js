/* 
 * (c) 2017 NewVi Cia. Ltda.
 *   * 
 */

PrimeFaces.widget.VisorGeografico = PrimeFaces.widget.BaseWidget.extend({
    init: function (cfg) {

        this._super(cfg);

        // Inicializar el componente de mapa
        var capaOsm = new ol.layer.Tile({
            source: new ol.source.OSM()
        });
        var ecuador = ol.proj.transform([-84, -1.75], 'EPSG:4326', 'EPSG:3857');
        var centro = new ol.View({
            center: ecuador,
            zoom: 6
        });
        var map = new ol.Map({
            target: cfg.id
        });
        map.addLayer(capaOsm);
        map.setView(centro);

        // Colocar un tamaño (ancho y alto)
        // Agregar un mapa base
        // Inicializar


        //this.startTime = cfg.time && cfg.mode !== 'client' ? moment(cfg.time) : moment();

        this.colorScheme = cfg.colorScheme || 'standard';

        //this.dimensions = new PrimeFaces.widget.VisorGeografico.Dimensions(this.cfg.width || this.jq.width());


        this.interval = setInterval((function (self) {
            return function () {
                self.update();
            }
        })(this), 1000);

        this.draw();
    },

    draw: function () {

        alert("hola mundo!");

        // [TODO] generar mapa
        //this.canvas = Raphael(this.id, this.dimensions.size, this.dimensions.size);


        //this.clock = this.canvas.circle(this.dimensions.half, this.dimensions.half, this.dimensions.clock_width);
        //this.clock.attr({
        //    "fill" : PrimeFaces.widget.VisorGeografico.colorSchemes[this.colorScheme].face,
        //    "stroke" :PrimeFaces.widget.VisorGeografico.colorSchemes[this.colorScheme].border,
        //   "stroke-width" : "5"
        //})


        //this.draw_hour_signs();


        //this.draw_hands();


        //var pin = this.canvas.circle(this.dimensions.half, this.dimensions.half, this.dimensions.pin_width);
        //pin.attr("fill", PrimeFaces.widget.VisorGeografico.colorSchemes[this.colorScheme].pin);


        this.update();
    },

    update: function () {
    }


});