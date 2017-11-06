add project inside src/main/your-angular-project of SpringBoot Project


changes name in two files as name of spring boot project

	1. package-lock.json
	2. package.json

Add file in angular project as name (proxy-conf.json)

1. proxy-conf.json(project root)

	{
	  "/api": {
	    "target": "http://localhost:2030",
	    "secure": false
	  }
	}
	
2. Replace scripts in package.json

	
	"scripts": {
		"ng": "ng",
		"start": "ng serve --proxy-config proxy-conf.json",
		"build": "ng build -prod",
		"postbuild": "npm run deploy",
		"predeploy": "rimraf ../resources/static/ && mkdirp ../resources/static",
		"deploy": "copyfiles -f dist/** ../resources/static",
		"test": "ng test",
		"pree2e": "webdriver-manager update --standalone false --gecko false",
		"e2e": "protractor"
	},
  
===========================	 and add dependencies after all @angular content in package .json	=========================
  	"bootstrap": "^3.3.7",
    "copyfiles": "^1.2.0",
    "core-js": "^2.4.1",
    "loader.js": "^4.0.1",
    "mkdirp": "^0.5.1",
    "moment": "^2.18.1",
    "ng2-toasty": "^4.0.3",
    "rimraf": "^2.6.1",
    "rsvp": "^3.3.2",
    "rxjs": "^5.0.1",
    "ts-helpers": "^1.1.1",
    "zone.js": "^0.8.11"
       
========================	 add devDependencies after all @ content	============================
    "codelyzer": "~3.1.1",
    "jasmine-core": "~2.6.2",
    "jasmine-spec-reporter": "~4.1.0",
    "karma": "~1.7.0",
    "karma-chrome-launcher": "~2.1.1",
    "karma-cli": "~1.0.1",
    "karma-coverage-istanbul-reporter": "^1.2.1",
    "karma-jasmine": "~1.1.0",
    "karma-jasmine-html-reporter": "^0.2.2",
    "protractor": "~5.1.2",
    "ts-node": "~3.2.0",
    "tslint": "~5.3.2",
    "typescript": "~2.3.3",
    "ember-cli-babel": "^5.1.6",
    "karma-remap-istanbul": "^0.6.0",
    "webpack": "^3.8.1"
  
==========================		Install plugin	==========================
  
	npm install ng2-toasty --save
	

=========================		Add Dependancy in pom.xml	==========================
	Add Dependancy in pom.xml
	
	<!-- <dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
		</dependency> -->
	
	
	
