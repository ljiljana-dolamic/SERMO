modules = {
	
	/**
	 * -------------------
	 * Overrides
	 * -------------------
	 */
	
//	overrides {
//		
//
//		jquery {
//			//defaultBundle 'third-party'
//			resource id:'js', bundle : 'core'
//		}
//
////		'jquery-ui' {
////			resource id:'js', bundle: 'third-party'
////			resource id:'theme', bundle: 'third-party'
////		}
////
////		'jquery-theme' {
////			resource id:'theme', bundle: 'third-party'
////		}
////
////		'blueprint' {
////			resource id:'main', bundle : 'third-party'
////			resource id:'ie',   bundle : 'blueprint'
////		}
//	}
	
	sermo {
		
		dependsOn 'jquery'
		
//		defaultBundle 'core'
		resource url: '/css/sermo/filters.css'
		resource url: '/css/sermo/sermo.css'
		resource url: '/css/sermo/navMenu.css'
		resource url: '/css/main.css'
		resource url: '/css/status.css'
		resource url: '/css/tipsy/tipsy.css'
		
		resource url : '/js/docPage-changer.js'
		resource url : '/js/filterShowHide.js'
		resource url: '/js/jquery/jquery.tipsy.js'
		resource url: '/js/jquery/jquery.elevatezoom.js'
		resource url : '/js/docPartShowChanger.js'
	}

	
	
	
   
	
}
