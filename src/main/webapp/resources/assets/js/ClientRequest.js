(function(window) {

	function ClientRequest() {
		var self = this;
		var methods = [ "post", "get" ];
		methods.forEach(function(method) {
			self[method] = function(url, params, succallback, errcallback) {
				self.request_(method, url, params, succallback, errcallback);
			};
		});
	}

	ClientRequest.prototype.postJSON = function(url, params, succallback, errcallback) {
		if (params && !succallback) {
			succallback = arguments[1];
		}
		if (params && !errcallback) {
			errcallback = arguments[1];
		}
		this.request_('post', url, params, 
		function(response) {
			succallback(response);
		},function(response) {
			errcallback(response);
		});
	};

	ClientRequest.prototype.getJSON = function(url, params, succallback, errcallback) {
		if (params && !succallback) {
			succallback = arguments[1];
		}
		if (params && !errcallback) {
			errcallback = arguments[1];
		}
		this.request_('get', url, params, 
		function(response) {
			succallback(response);
		},function(response) {
			errcallback(response);
		});
	};
	

	ClientRequest.prototype.request_ = function(method, url, params, succallback, errcallback) {
		var args = {}, key, _succallback = succallback || params, _errcallback = errcallback || params;

		if (typeof params == 'object') {
				for (key in params) {
					 args[key] = params[key];
				}
		}
      
		$.ajax({
            type: method,
            async: "false",
            data: args,
            url: url,
            dataType: 'json',
            success: function(response){
               //returnobj=JSON.parse(jsonstr);
               _succallback(response);
            },
            error: function(response){
            	 _errcallback(response);
            }
        });

	};

		if (!window.Tcsp) {
			window.Tcsp = {};
		}
		window.Tcsp.ClientRequest = ClientRequest;
		
})(window);