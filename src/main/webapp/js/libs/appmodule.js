(function () {
    var app = angular.module('modonex', ['ngRoute']);

    app.config(function ($routeProvider) {
        $routeProvider


                // route for the contact page
                .when('/list', {
                    templateUrl: 'productos.html'

                })
                .when('/prod', {
                    templateUrl: 'index.html'

                });

    });

     app.controller('controlregistro',
        function($http){
                 this.detalleproducto=
                        {
                             idproducto:1,nombre:'prod',precio:0
                        };
                 this.registrar=function(){
                     $http.post('rest/products', this.detalleproducto).
                        success(function (data, status, headers, config) {
                            alert('success!');
                         }).
                        error(function (data, status, headers, config) {
                            alert('error!');
                         });
                  
                  };       
         }
     );
     /*-----------------------------------------------------------------*/
     app.controller('ConsultaCatalogo', 
             function($scope, $http) {
                 
                    $scope.productos=[];                   
            this.consultar=function(){        
                    $http.get('rest/productos')
                       .success(function (response) {
                           $scope.productos = response;
                           console.log("RESPUESTA:"+response);
                        }).
                       error(function(data, status, headers, config) {
                           //console.log("ERROR:"+status);
                            // called asynchronously if an error occurs
                            // or server returns response with an error status.
  
                       
                        });
                    }; 
            } 
     );
    /* <---------------------------------------------------------------->*/
    app.controller('ConsultaProducto', 
             function($scope, $http) {
                 
     
                    $scope.descripcion="producto";
                    $scope.precioLista=0;
                    $scope.idproductos=1;
            this.consultar=function(){        
                    $http.get('rest/producto/'+$scope.idproductos)
                       .success(function (response) {
                       
                           $scope.descripcion = response.descripcion;
                           $scope.precioLista=response.precioLista;
                           console.log("RESPUESTA:"+response);
                        }).
                       error(function(data, status, headers, config) {
                         
                           //console.log("ERROR:"+status);
                            // called asynchronously if an error occurs
                            // or server returns response with an error status.
  
                       
                        });
                    }; 
            } 
     );
  /*------------------------------------------------------------------------*/
  app.controller('ConsultaProveedor', 
             function($scope, $http) {
                 
     
                    $scope.proveedor=[];
            this.consultar=function(){        
                    $http.get('rest/proveedor')
                       .success(function (response) {
                           $scope.proveedor = response;
                           console.log("RESPUESTA:"+response);
                        }).
                       error(function(data, status, headers, config) {
                         
                           //console.log("ERROR:"+status);
                            // called asynchronously if an error occurs
                            // or server returns response with an error status.
  
                       
                        });
                    }; 
            } 
     );
   /*---------------------------------------------------------------------------*/
   app.controller('ConsultaProdProveedor', 
             function($scope, $http) {
                 
     
                    $scope.idproveedor=1;
                    $scope.productos=[];
            this.consultar=function(){        
                    $http.get('rest/ProveedorProd/'+$scope.idproveedor)
                       .success(function (response) {
                           $scope.productos = response;
                           console.log("RESPUESTA:"+response);
                        }).
                       error(function(data, status, headers, config) {
                         
                           //console.log("ERROR:"+status);
                            // called asynchronously if an error occurs
                            // or server returns response with an error status.
  
                       
                        });
                    }; 
            } 
     );
    
})();





