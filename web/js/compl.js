var datastrs ="";
var currentIndex = -1;
var strscopy = "";
var comdivid = "";
var comptextid = "";
var fullcode = "";

function autocomplateh()
{
    if (event.keyCode != 38&&event.keyCode != 40&&event.keyCode != 13) {

        filldiv();
    }else{

        filldiv();
        var autocomplate = document.getElementById(comdivid);
        if (event.keyCode == 38) {
            autocomplate.style.display="";
            currentIndex --;
        }

        if (event.keyCode == 40) {
            autocomplate.style.display="";
            currentIndex ++;
        }

        if(currentIndex<0)currentIndex=0;
        if(currentIndex>strscopy.split("--").length-1)currentIndex=strscopy.split("--").length-1;

        if (event.keyCode == 13) {
            var actionCodeText =  document.getElementById(comptextid);
            actionCodeText.value = strscopy.split("--")[currentIndex];
            autocomplate.innerHTML = "";
            autocomplate.style.display="none";
        }else{

            for(var i=0;i<strscopy.split("--").length;i++)
            {
                var autodiv = document.getElementById("autodiv"+i);
                if(i==currentIndex)
                {
                    autodiv.style.background="#B9CDF6";
                }else{
                    autodiv.style.background="";
                }
                if(i==14)break;
            }
        }

    }

}


function filldiv()
{

    strscopy = "";
    var fvalue = document.getElementById(comptextid).value;

    for(var i=0;i<datastrs.split("--").length;i++)
    {

        var strsp = datastrs.split("--")[i];
        var fvalues = fvalue.split(" ");
        var y = 1;
        for(var x=0;x<fvalues.length;x++){
            if(fvalues[x]==" ")continue;
            if(strsp.indexOf(fvalues[x])==-1){
                y = 0;
            }
        }

        if(y==1)
        {
            strscopy+=datastrs.split("--")[i]+"--";
        }
    }


    if(strscopy.length>0)strscopy=strscopy.substring(0,strscopy.length-2);
    document.getElementById(comdivid).style.display="";

    var ihtml = "";
    var arraystrs = strscopy.split("--");
    for(var i=0;i<arraystrs.length;i++)
    {
        var dis = "";
        if(i>14)dis="none";
        ihtml+="<div style=\"padding: 6px;display:"+dis+"\" id=\"autodiv"+i+"\" onclick=\"confirmCode('"+arraystrs[i]+"')\" onMouseOver=\"this.style.background='#B9CDF6'\" onMouseOut=\"this.style.background=''\"><font style=\"font-size:13px\">";
        //ihtml+=arraystrs[i];
        var itemstr = "";
        var itemstrs = arraystrs[i].split(" - ");
        for(var x=0;x<itemstrs.length;x++)
        {
            if(x==0)
            {
                itemstr+=itemstrs[x]+" &nbsp;";
            }else{
                itemstr+="&nbsp;&nbsp;<font color=gray>"+itemstrs[x]+"</font> ";
            }
        }
        ihtml+=itemstr;

        ihtml+="</font></div>";

    }

    ihtml+="<center><div style=\"padding: 3px;display: inline;padding: 3px\" id=\"autodiv"+i+"\" onclick=\"clearAuto()\" onMouseOver=\"this.style.background='#B9CDF6'\" onMouseOut=\"this.style.background=''\"><font style=\"font-size:13px\">";
    ihtml+=" - CLEAR - ";
    ihtml+="</font></div>";

    ihtml+="<div style=\"width: 35px;display: inline\" >&nbsp;&nbsp;&nbsp;</div>";

    ihtml+="<div style=\"padding: 3px;display: inline;padding: 3px\" id=\"autodiv"+i+"\" onclick=\"closeAuto();\" onMouseOver=\"this.style.background='#B9CDF6'\" onMouseOut=\"this.style.background=''\"><font style=\"font-size:13px\">";
    ihtml+="  - CLOSE - ";
    ihtml+="</font></div></center>";


    var autocomplate =  document.getElementById(comdivid);
    autocomplate.innerHTML=ihtml;
}

function removeAuto()
{
    var actionCodeText =  document.getElementById(comptextid).value;
    if(actionCodeText!=''){
        var autodiv = document.getElementById(comdivid);
        autodiv.innerHTML = "";
        autodiv.style.display="none";
        var v =  "--"+document.getElementById(comptextid).value;
        var v2 =  ":"+document.getElementById(comptextid).value;
        if(("--"+datastrs+"--").indexOf(v)==-1&&("--"+datastrs+"--").indexOf(v2)==-1)
        {
            document.getElementById(comptextid).value='';
        }
        if(datastrs.indexOf(actionCodeText )==-1)
        {
            document.getElementById(comptextid).value="";
        }
    }
}

function closeAuto()
{
    var autodiv = document.getElementById(comdivid);
    autodiv.innerHTML = "";
    autodiv.style.display="none";
}

function clearAuto()
{
    document.getElementById(comptextid).value='';
    removeAuto();
}

function confirmCode(code)
{
    var autocomplate = document.getElementById(comdivid);
    var actionCodeText =  document.getElementById(comptextid);
    var cvalue = code.split(" - ")[0];
    if(cvalue.indexOf(":")>-1)
    {
        cvalue = code.split(" - ")[0].split(":")[1];
    }
    actionCodeText.value = cvalue;
    autocomplate.innerHTML = "";
    autocomplate.style.display="none";
}


function clearvalue()
{
    var v =  "--"+document.getElementById(comptextid).value;
    var v2 =  ":"+document.getElementById(comptextid).value;
    if(v.length>2){
        if(("--"+datastrs+"--").indexOf(v)==-1&&("--"+datastrs+"--").indexOf(v2)==-1){
            document.getElementById(comptextid).value='';
        }
    }



}

