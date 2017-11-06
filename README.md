Step-1
	Add project inside src/main/{your-angular-project}

Step-2
	Change spring-boot project name as name in two files

	1. package-lock.json
	2. package.json

Step-3
	Add file in angular project as name (proxy-conf.json)

	{
	  "/api": {
	    "target": "http://localhost:2030",
	    "secure": false
	  }
	}
	
Step-4
	Replace script content in package.json

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
  
Add dependencies after all @angular content in package .json

		"bootstrap": "^3.3.7",
		"copyfiles": "^1.2.0",
		"core-js": "^2.4.1",
		"loader.js": "^4.0.1",
		"mkdirp": "^0.5.1",
		"moment": "^2.18.1",		
		"rimraf": "^2.6.1",
		"rsvp": "^3.3.2",
		"rxjs": "^5.0.1",
		"ts-helpers": "^1.1.1",
		"zone.js": "^0.8.11",
		"ng2-toasty": "^4.0.3"
       
Add devDependencies after all @ content

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
  
Install plugin
  
	npm install ng2-toasty --save
	

Add Dependancy in pom.xml
	
		<!-- <dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
		</dependency> -->
	
	
	
