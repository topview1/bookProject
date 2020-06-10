/**
 * 用来检验订单的登录信息
 */

   var receiverAddress = document.getElementById("receiverAddress");
   var receiverName = document.getElementById("receiverName");
   var receiverPhone  = document.getElementById("receiverPhone");
   var receiverAddressMsg = document.getElementById("receiverAddressMsg");
   function validateForm(){
	   return checkReceiverAddress();
   }
   
   /**
    * 检验登录信息
    * @returns
    */
   function checkReceiverAddress(){
	   if (receiverAddress.value == null || receiverAddress.value == "") {
		   receiverAddressMsg.innerHTML = "收获地址不能为空"
	        return false;
	    }
	   return true;
   }