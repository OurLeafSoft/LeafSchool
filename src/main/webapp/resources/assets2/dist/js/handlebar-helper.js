/* $Id: $ */
var i18n = {};
Handlebars.registerHelper('ifCond', function(v1, v2, options) { //NO I18N
  if(v1 == v2) {
    return options.fn(this);
  }
  return options.inverse(this);
});

Handlebars.registerHelper('ifNot', function(v1, v2, options) { //NO I18N
  if(v1 != v2) {
    return options.fn(this);
  }
  return options.inverse(this);
});

/*
Handlebars.registerHelper('multiple', function(v1, v2) { //NO I18N
  var total = parseInt(v1) * parseInt(v2);
  return total;
}); previous one before multi currency */

Handlebars.registerHelper('multiple', function(v1, v2) { //NO I18N
    var v1 = v1[activeCurrency];
    var v2 = v2 != null ? v2 : 1;
    var total = parseFloat(v1) * parseFloat(v2);
    return total.toFixed(2);
});
/*Handlebars.registerHelper('currency', function(v1, v2, options) { //NO I18N
    if(v1 == null){
        return "";
    }else{
        if(v2 == "calPrice"){
            return v1[activeCurrency];
        } else {
            return formatAmount(v1[activeCurrency], true);
        }
    }
});*/
Handlebars.registerHelper('formatAmount', function(v1, options) { //NO I18N
    if(v1){
    	return formatAmount(v1, true);
    	//return v1[activeCurrency];
    }
});
Handlebars.registerHelper('currencyFView', function(v1, options) { //NO I18N
    if(v1){
    	return formatAmount(v1[activeCurrency], true);
    	//return v1[activeCurrency];
    }
});
/*Handlebars.registerHelper('currencyFView', function(v1,v2, options) { //NO I18N
    if(v1){
    	var res = v2 ? formatAmount(v1[activeCurrency],true,true) : formatAmount(v1[activeCurrency], true);
    	return res;
    }
});*/
Handlebars.registerHelper('currency', function(v1, options) { //NO I18N
	if(v1) {
		return v1[activeCurrency];
	}
});
/*above js For Multi-Currency */
Handlebars.registerHelper('i18n', function(key,p0,p1,p2,options) { //NO I18N
		var val = i18n[key];
		if(val != null && val != undefined) {
			if(p0) {
				val = val.replace('{0}',p0);
				if(p1) {
					val = val.replace('{1}',p1);
					if(p2) {
						val = val.replace('{2}',p2);
					}
				}
			}
			return new Handlebars.SafeString(val);
		}
		return new Handlebars.SafeString(key);
});

Handlebars.registerHelper('twoVariables', function(v1, v2, options) { //NO I18N
  if(v1 || v2) {
    return options.fn(this);
  }
  return options.inverse(this);
});

Handlebars.registerHelper('isNotEmpty', function(v1) { //NO I18N
  if(v1 && v1.length > 0) {
    return true;
  }
  return false;
});

Handlebars.registerHelper('isEmpty', function(v1) { //NO I18N
  if(!v1) {
    return true;
  }
  return false;
});

Handlebars.registerHelper('for', function(min, max, step, arr) { //NO I18N
	var n = 0;
    for(var i = min; i <= max; i = i + step) {
    	var obj = {};
    	obj.value = i;
    	arr[n] = obj; n++;
    }
});


var selectedPlan = 'Premium';//NO I18N
Handlebars.registerHelper('selectedPlan', function(v1, options) { //NO I18N
	var v2 = selectedPlan;
	if(v1 == v2) {
	return options.fn(this);
	}
	return options.inverse(this);
});

Handlebars.registerHelper('orOperator', function(v1, v2, options) { //NO I18N
  if(v1 || v2) {
    return options.fn(this);
  }
  return options.inverse(this);
});

Handlebars.registerHelper('orOperator3', function(v1, v2, v3, options) { //NO I18N
  if(v1 || v2 || v3) {
    return options.fn(this);
  }
  return options.inverse(this);
});


//new helper added
Handlebars.registerHelper('singlePlanNoAddOnPDB', function(planType, planLength, options) {//NO I18N
    var type1 = planType.split('-')[0] === 'PDB'; //NO I18N
    var type2 = planType.split('-')[1] === 'UUB'; //NO I18N
    var planLength = planLength === 1;

    if(type1 && type2 && planLength) {
        return options.fn(this);
    }
    return options.inverse(this);
});

var selectedPlan = 'Premium';  //NO I18N
Handlebars.registerHelper('selectedPlan', function(v1, options) { //NO I18N
  	var v2 = selectedPlan;
  	if(v1 == v2) {
  		  return options.fn(this);
  	}
  	return options.inverse(this);
});
Handlebars.registerHelper('highlightFeature', function(v1,v2, options) { //NO I18N
  	if(v1 == v2) {
  		  return options.fn(this);
  	}
  	return options.inverse(this);
});

Handlebars.registerHelper('getFeatureValue', function(v1,v2, options) { //NO I18N
    var features = v2[v1];
    if(features == false){
        return x;
    } else if(features == true){
        return '✓';
   
    }else{
        return features;
    }
});
Handlebars.registerHelper('serviceType', function(type, value, options) { //NO I18N

    var type = type.split('-')[0];
    if(type == value){

        return options.fn(this);
    } else {
        return options.inverse(this);
    }
});

Handlebars.registerHelper('serviceTypeSub', function(typeSub, subValue, options) { //NO I18N
    var type = typeSub.indexOf('-') > -1 ? typeSub.split('-')[1] : typeSub;
    if(type == subValue){
       
        return options.fn(this);
    } else {
        return options.inverse(this);
    }
});
Handlebars.registerHelper('planType', function(planType, val, options) { //NO I18N
	if(planType === "UB") {
		if(val === ("UB") || val === ("SUB")) {
			return options.fn(this);
		} else {
	        return options.inverse(this);
	    }
	}
		
    if(planType.indexOf(val) > -1){
        return options.fn(this);
    } else {
        return options.inverse(this);
    }
});
Handlebars.registerHelper('nubSinglePlanNoAddOn', function(planLength, options) { //NO I18N
    if(planLength == 1){
        $('#detailedPlan,.planDetailHd,.planDetails').addClass('hide');//NO I18N
        $('.paymentDetails .planNos').text('3');
        $('.confirmOrderTitle .planNos').text('2');
        $('.subscription-title .stepCount').text('3');
        return options.fn(this);
    } else {
        return options.inverse(this);
    }
});
Handlebars.registerHelper('isPaymentDuration', function(v1, options) { //NO I18N
    var v2 = paymentDuration;
    if(v1 == v2) {
        return options.fn(this);
    }
    return options.inverse(this);
});


Handlebars.registerHelper("debug", function(optionalValue) { //NO I18N
    /*console.log("Current Context");//NO I18N
    console.log("====================");//NO I18N
    console.log(this);
    if (optionalValue) {
        console.log("Value");
        console.log("====================");//NO I18N
        console.log(optionalValue);
    }*/
});

Handlebars.registerHelper('ifOperator', function (v1, operator, v2, options) { //NO I18N  
    switch (operator) {																				 //{{#ifOperator var1 '==' var2}} NO I18N 
        case '==':  // Equality Check
            return (v1 == v2) ? options.fn(this) : options.inverse(this);
        case '!=':  // Not
            return (v1 != v2) ? options.fn(this) : options.inverse(this);
        case '===': // Equality and type Check
            return (v1 === v2) ? options.fn(this) : options.inverse(this);
        case '<':   // Lesser than
            return (v1 < v2) ? options.fn(this) : options.inverse(this);
        case '<=':  // Lesser than and Equal
            return (v1 <= v2) ? options.fn(this) : options.inverse(this);
        case '>':   // Greater than
            return (v1 > v2) ? options.fn(this) : options.inverse(this);
        case '>=':  // Greater than and Equal
            return (v1 >= v2) ? options.fn(this) : options.inverse(this);
        case '&&':  // AND operator
            return (v1 && v2) ? options.fn(this) : options.inverse(this);
        case '||':  // OR Operator
            return (v1 || v2) ? options.fn(this) : options.inverse(this);
        default:
            return options.inverse(this);
    }
});
//For Meeting / Assist
Handlebars.registerHelper('secondaryLicense', function( v1, v2, options) { //NO I18N
    var licenses = secondaryOptedLicense(),
        selectedPlanID = StoreProperties.renderingJSON.planTypes[0].selectedPlan;
        price =  jsonPath(StoreProperties.renderingJSON, "$.planTypes[*].plans[?(@.plan =="+selectedPlanID+")].secondaryLicenseDetails.licenses[?(@.license == "+licenses+")].price["+activeCurrency+"]");//NO I18N
    if(v1 ==  "license"){
            return  licenses;
    } else if(v1 ==  "price"){ //NO I18N
            return price;
    } else if (v1 == "total" && v2){ //NO I18N
        var total = price*v2;
        return formatAmount(total, "decimal");//NO I18N
    }
    
});
Handlebars.registerHelper('setUnits', function( v1, v2, options) {  //NO I18N  
    if(v1 > 1){
        return  v2+"s"; //NO I18N  
    } else {
        return v2;
    }
    
});
Handlebars.registerHelper('getFeaturesValue', function(v1,v2, options) {//NO I18N  
    var features = v2[v1];
    if(features === true){
        return new Handlebars.SafeString('<span class="feature-available"></span>');
    } else if(features === false ){
        return new Handlebars.SafeString('<span class="feature-notavailable"></span>');
    }else{
        return features;
    }
});
Handlebars.registerHelper('planDescription', function(v1,v2, options) {//NO I18N  
    var featuresList = v1[v2];
    var returnStr = "";
	for(i=0; featuresList !== undefined && i<featuresList.length; i++ ){
		returnStr += '<li>'+i18n[featuresList[i]]+'</li>';
	}
	return new Handlebars.SafeString(returnStr);
});
Handlebars.registerHelper('populatUnitOption', function(v1,v2, options) { //NO I18N
	var optionCollection = "";
	var pricePerUnit = v2[activeCurrency];
	for(var i=1;i<=v1;i++){
		optionCollection += "<option price="+i*pricePerUnit+" value="+i+">"+i+"</option>";
	}
    
    return optionCollection;
});
Handlebars.registerHelper('toLowerCase', function(v1, options) {//NO I18N  
   if(v1){
	   v1 = v1.toLowerCase();
   }
   return v1;
});
/*
Handlebars.registerHelper('populateYear', function(v1,v2, options) { //NO I18N
	var currentYear = parseInt(new Date().getFullYear().toString().substr(2,2)),
	totalYear = currentYear + 20;
	for(var i = currentYear; i <= totalYear;i++){
		optionList += "<option year="+i+">"+i+"</option>";
	}
    return optionList;
});*/