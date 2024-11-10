(function($) {
  'use strict';
var dataSet = [
    [ "<img src='../../assets/img/dashboard/patient-1.jpg' > Denise", "Old York, Abington", "Fever", "7", "	(836) 257 1379", "denisestevens@example.com", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='a'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
    [ "<img src='../../assets/img/dashboard/patient-2.jpg'> Dennis", "Juniper Drive, Saginaw", "Cancer", "34", "(933) 137 6201", "dennissalazar@example.com", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='a'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
    [ "<img src='../../assets/img/dashboard/patient-3.jpg'> Jennifer", "Leland, NC", "Heart Attack", "35", "(207) 808 8863", "jenniferrobinson@example.com", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='a'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
    [ "<img src='../../assets/img/dashboard/patient-4.jpg'> Joshua", "Bonita Springs", "Cold", "34", "	(407) 554 4146", "	joshuaguzman@example.com", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='a'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
    [ "<img src='../../assets/img/dashboard/patient-5.jpg'> Charles", "Birch Street, El Paso", "Cancer", "	32", "(380) 141 1885", "charlesortega@example.com", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='a'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
    [ "<img src='../../assets/img/dashboard/patient-6.jpg'> Judy Clark", "Woodside Circle, Pensacola", "Diabeties", "34", "	(359) 969 3594", "judy.clark@example.com", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='a'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
    [ "<img src='../../assets/img/dashboard/patient-7.jpg'>  Julia Sims", "Walker Dr, Houma, LA, United States", "Celiac ", "27", "	(680) 432 2662", "juliasims@example.com", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='a'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
    [ "<img src='../../assets/img/dashboard/patient-8.jpg'>  Kyle", "Fairways Cir, Vero Beach", "Fever", "	7", "	(981) 756 6128", "	kylebowman@example.com", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='a'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
    [ "<img src='../../assets/img/dashboard/patient-9.jpg'>  Linda", "Victory Garden, Tallahassee", "Heart Attack", "24", "(218) 661 8316", "	lindacarpenter@example.com", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='a'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
    [ "<img src='../../assets/img/dashboard/patient-1.jpg'>  Marie", "New Haven, Columbia", "Cold", "22", "(634) 09 3833", "mariehoward@example.com", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='a'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
    [ "<img src='../../assets/img/dashboard/patient-2.jpg'> Melissa", "Milwaukee, WI", "Cancer", "35", "(192) 494 8073", "melissaburton@example.com", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='a'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
    [ "<img src='../../assets/img/dashboard/patient-3.jpg'>  Patrick", "Commerce, TX", "Celiac ", "21", "(785) 580 4514", "patrickknight@example.com", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='a'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
    [ "<img src='../../assets/img/dashboard/patient-4.jpg'>  Sandra", "Linden Avenue, Orlando", "Liver Disease", "24", "	(797) 506 1265", "sandramendez@example.com", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='a'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
    [ "<img src='../../assets/img/dashboard/patient-5.jpg'>  Terry Baker", "Hempstead, NY", "Diabeties", "63", "(376) 150 6975", "	terrybaker@example.com", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='a'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
    [ "<img src='../../assets/img/dashboard/patient-6.jpg'>  Tatyana","Saginaw, MI,", "Fever ", "34", "(933) 137 6201", "tatyanafitzpatrick@example.com", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='a'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
    [ "<img src='../../assets/img/dashboard/patient-7.jpg'>  Michael",  "Linden Avenue, Orlando", "Celiac ", "24", "	(797) 506 1265", "michaelsilva@example.com", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='a'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
    [ "<img src='../../assets/img/dashboard/patient-8.jpg'>  Paul Byrd", "Milwaukee, WI", "Asthma", "35", "(192) 494 8073", "paulbyrd@example.com", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='a'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
    [ "<img src='../../assets/img/dashboard/patient-9.jpg'>  Gloria ", "LA, United States", "Cold", "27", "	(680) 432 2662", "glorialittle@example.com", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='a'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
    [ "<img src='../../assets/img/dashboard/patient-1.jpg'>  Bradley", "Victory Garden, Tallahassee", "Liver Disease", "24", "(218) 661 8316", "	bradleygreer@example.com", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='a'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
    [ "<img src='../../assets/img/dashboard/patient-2.jpg'>  Dai Rios", "Fairways Cir, Vero Beach", "Heart Attck", "	7", "	(981) 756 6128", "dairiosn@example.com", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='a'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
    [ "<img src='../../assets/img/dashboard/patient-3.jpg'>  Jenette ", "Birch Street, El Paso", "Autoimmune", "	32", "(380) 141 1885", "jenettecaldwell@example.com", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='a'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
    [ "<img src='../../assets/img/dashboard/patient-4.jpg'>  Yuri Berry", " LA, United States", "Fever", "27", "	(680) 432 2662", "yuriberry@example.com", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='a'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
    [ "<img src='../../assets/img/dashboard/patient-5.jpg'>  Caesar ", "Birch Street, El Paso", "Asthma", "	32", "(380) 141 1885", "caesarvance@example.com", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='a'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
    [ "<img src='../../assets/img/dashboard/patient-6.jpg'>  Doris ", "Victory Garden, Tallahassee", "Diabeties", "24", "(218) 661 8316", "	doriswilder@example.com", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='a'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
    [ "<img src='../../assets/img/dashboard/patient-7.jpg'>  Angelica",  "Linden Avenue, Orlando","Liver Disease",  "24", "	(797) 506 1265", "angelicaramos@example.com", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='a'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
    [ "<img src='../../assets/img/dashboard/patient-8.jpg'>  Joyce", "Milwaukee, WI", "Asthma", "35", "(192) 494 8073", "gavinjoyce@example.com", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='a'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
    [ "<img src='../../assets/img/dashboard/patient-9.jpg'>  Jennifer ", "Milwaukee, WI","Autoimmune",  "35", "(192) 494 8073", "jenniferchang@example.com", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='a'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
    [ "<img src='../../assets/img/dashboard/patient-1.jpg'>  Brenden",  "New Haven, Columbia", "Infectious", "22", "(634) 09 3833", "brendenwagner@example.com", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='a'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
    [ "<img src='../../assets/img/dashboard/patient-2.jpg'>  Fiona ", "LA, United States", "Colitis", "27", "	(680) 432 2662", "fionagreen@example.com", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='a'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
    [ "<img src='../../assets/img/dashboard/patient-3.jpg'>  Shou Itou", "Fairways Cir, Vero Beach", "Asthma", "	7", "	(981) 756 6128", "shouitou@example.com", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='a'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
    [ "<img src='../../assets/img/dashboard/patient-4.jpg'>  Michelle",  "Linden Avenue, Orlando","Infectious",  "24", "	(797) 506 1265", "Michelle@example.com", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='a'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
    [ "<img src='../../assets/img/dashboard/patient-5.jpg'>  Suki Burks","Milwaukee, WI", "Colitis", "35", "(192) 494 8073", "sukiburks@example.com", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='a'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
    [ "<img src='../../assets/img/dashboard/patient-6.jpg'>  Prescott", "Milwaukee, WI", "Infectious", "35", "(192) 494 8073", "prescottbartlett@example.com", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='a'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
    [ "<img src='../../assets/img/dashboard/patient-7.jpg'>  Gavin ", "Fairways Cir, Vero Beach", "Autoimmune", "	7", "	(981) 756 6128", "gavincortez@example.com", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='a'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
    [ "<img src='../../assets/img/dashboard/patient-8.jpg'>  Martena", "LA, United States", "Diabeties", "27", "	(680) 432 2662", "martenamccray@example.com", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='a'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
    [ "<img src='../../assets/img/dashboard/patient-9.jpg'>  Unity",  "New Haven, Columbia", "Celiac ", "22", "(634) 09 3833", "butler@example.com", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='a'><i class='far fa-trash-alt ms-text-danger'></i></a>" ]
  ];



  var dataSet1 = [
      [ "1", "Denise", "Dentists","Daniel", "<span class='badge badge-outline-success'>Active</span>",   "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
      [ "2", "Jennifer", "Neurology","Joshua", "<span class='badge badge-outline-danger'>Inactive</span>",  "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
      [ "3",  "Joshua","Opthalmology","Jenni", "<span class='badge badge-outline-danger'>Inactive</span>",  "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
      [ "4",  "Sandra", "Orthopedics","Daniel", "<span class='badge badge-outline-success'>Active</span>",   "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
      [ "5", "Judy Clark", "Cancer Department","Jacob", "<span class='badge badge-outline-danger'>Inactive</span>", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
      [ "6",  "Linda", "ENT Department","Adwerd", "<span class='badge badge-outline-danger'>Inactive</span>",  "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
      [ "7","Micheal", "General Surgery","Kyle", "<span class='badge badge-outline-success'>Active</span>", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
      [ "8", "Patrick",  "Heart Surgery","Paul", "<span class='badge badge-outline-danger'>Inactive</span>",   "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
      [ "9", "Paul", "Radiotherapy","Patrick", "<span class='badge badge-outline-success'>Active</span>",   "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
      [ "10", "Gloria", "Cancer Department","Sandra", "<span class='badge badge-outline-danger'>Inactive</span>",  "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
      [ "11","Deni", "	Gynaecology","Denis", "<span class='badge badge-outline-success'>Active</span>",  "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
      [ "12","Jenni", "Orthopedics","Bella", "<span class='badge badge-outline-danger'>Inactive</span>", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
      [ "13", "Joshua", "Heart Surgery","Henry", "<span class='badge badge-outline-success'>Active</span>",  "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
      [ "14", "Kyle",  "Neurology", "Linda","<span class='badge badge-outline-success'>Active</span>",  "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],

    ];


    var dataSet2 = [
        [ "1", "Denise", "Neurology", "Sunday", "9:00", "11:00",   "<span class='badge badge-outline-success'>Active</span>",   "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
        [ "2", "Jennifer", "Heart Surgery", "Monday", "9:00", "11:00",   "<span class='badge badge-outline-danger'>Inactive</span>",  "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
        [ "3", "Joshua", "Gynaecology", "Saturday", "9:00", "11:00",   "<span class='badge badge-outline-success'>Active</span>",  "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
        [ "4", "Judy Clark", "Orthopedics", "Tuesday", "9:00", "11:00", "<span class='badge badge-outline-success'>Active</span>",   "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
        [ "5", "Kyle", "Radiotherapy", "Sunday", "9:00", "11:00",  "<span class='badge badge-outline-danger'>Inactive</span>", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
        [ "6", "Linda", "ENT Department", "Sunday", "9:00",  "11:00", "<span class='badge badge-outline-success'>Active</span>",  "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
        [ "7", "Sandra", "General Surgery", "Tuesday", "9:00", "11:00", "<span class='badge badge-outline-success'>Active</span>", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
        [ "8", "Micheal", "Heart Surgery", "Sunday", "9:00", "11:00",  "<span class='badge badge-outline-danger'>Inactive</span>",   "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
        [ "9", "Paul", "Radiotherapy", "Sunday", "9:00", "11:00",  "<span class='badge badge-outline-success'>Active</span>",   "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
        [ "10", "Terry", "Cancer Department", "Sunday", "9:00", "11:00",  "<span class='badge badge-outline-danger'>Inactive</span>",  "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
        [ "11", "Gloria", "	Gynaecology", "Tuesday", "9:00", "11:00",   "<span class='badge badge-outline-success'>Active</span>",  "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
        [ "12", "Charles", "Orthopedics", "Sunday", "9:00", "11:00",  "<span class='badge badge-outline-success'>Active</span>", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
        [ "13", "Patrick", "Heart Surgery", "Saturday", "9:00", "11:00",  "<span class='badge badge-outline-success'>Active</span>",  "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
        [ "14", "Dennis", "Neurology", "Friday", "9:00",  "11:00",  "<span class='badge badge-outline-success'>Active</span>",  "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],

      ];




      var dataSet3 = [
          [ "APT0001", "<img src='../../assets/img/dashboard/patient-1.jpg'>Denise", "39", "Henry Daniels", "Cardiology", "11 Dec 2019", "10:00am-12:00am", "Cold",  "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
          [ "APT0002", "<img src='../../assets/img/dashboard/patient-8.jpg'>Jennifer", "29", "Doris Wilder", "Gynaecology", "5 Dec 2019", "10:00am-12:00am",  "Fever",  "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
          [ "APT0003", "<img src='../../assets/img/dashboard/patient-4.jpg'>Joshua", "42", "Gavin Joyce", "Neurology", "6 Jan 2020", "10:00am-12:00am",  "heart", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
          [ "APT0004", "<img src='../../assets/img/dashboard/patient-9.jpg'>Judy", "23", "Yuri Berry", "Orthopedics", "19 Dec 2019", "10:00am-12:00am", "Diabeties", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
          [ "APT0005", "<img src='../../assets/img/dashboard/patient-2.jpg'>Kyle", "55", "Henry Daniels", "Radiotherapy","15 Dec 2019", "10:00am-12:00am", "Cold",  "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
          [ "APT0006", "<img src='../../assets/img/dashboard/patient-3.jpg'>Denise", "39", "Henry Daniels", "Cardiology","11 Dec 2019", "10:00am-12:00am", "heart",   "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
          [ "APT0007", "<img src='../../assets/img/dashboard/patient-7.jpg'>Jennifer", "29", "Doris Wilder", "Gynaecology", "5 Dec 2019", "10:00am-12:00am","Diabeties", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
          [ "APT0008", "<img src='../../assets/img/dashboard/patient-3.jpg'>Joshua", "42", "Gavin Joyce", "Neurology", "6 Jan 2020", "10:00am-12:00am",  "Fever", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
          [ "APT0009", "<img src='../../assets/img/dashboard/patient-8.jpg'>Judy", "23", "Yuri Berry", "Orthopedics", "19 Dec 2019", "10:00am-12:00am", "Asthma",  "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
          [ "APT000a0", "<img src='../../assets/img/dashboard/patient-4.jpg'>Kyle", "55", "Henry Daniels", "Radiotherapy", "15 Dec 2019", "10:00am-12:00am", "heart",  "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
          [ "APT000a1", "<img src='../../assets/img/dashboard/patient-6.jpg'>Denise", "39", "Henry Daniels", "Cardiology", "11 Dec 2019", "10:00am-12:00am",  "Cold",    "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
          [ "APT000a2", "<img src='../../assets/img/dashboard/patient-9.jpg'>Jennifer", "29", "Doris Wilder", "Gynaecology", "5 Dec 2019", "10:00am-12:00am", "Asthma",   "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
          [ "APT000a3", "<img src='../../assets/img/dashboard/patient-3.jpg'>Denise", "39", "Henry Daniels", "Cardiology", "11 Dec 2019", "10:00am-12:00am",  "Fever",   "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
          [ "APT000a4", "<img src='../../assets/img/dashboard/patient-1.jpg'>Jennifer", "29", "Doris Wilder", "Gynaecology", "5 Dec 2019", "10:00am-12:00am",  "Diabeties",  "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
          [ "APT000a5", "<img src='../../assets/img/dashboard/patient-3.jpg'>Denise", "39", "Henry Daniels", "Cardiology", "11 Dec 2019", "10:00am-12:00am", "heart",     "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
          [ "APT000a6", "<img src='../../assets/img/dashboard/patient-7.jpg'>Jennifer", "29", "Doris Wilder", "Gynaecology", "5 Dec 2019", "10:00am-12:00am",  "Cold",  "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],

        ];


        var dataSet4 = [
            [ "1", "Hospital Memo", "Lorem Ipsum is simply dummy text of the printing.", "Debit", "10 Nov 2019","340$", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
            [ "2", "Hospital Memo", "Lorem Ipsum is simply dummy text of the printing.", "Credit", "16 Nov 2019","1040$", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
            [ "3", "Hospital Memo", "Lorem Ipsum is simply dummy text of the printing.", "Credit", "12 Nov 2019","740$", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
            [ "4", "Hospital Memo", "Lorem Ipsum is simply dummy text of the printing.", "Debit", "16 Nov 2019","310$", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
            [ "5", "Hospital Memo", "Lorem Ipsum is simply dummy text of the printing.", "Debit", "8 Nov 2019","900$", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
            [ "6", "Hospital Memo", "Lorem Ipsum is simply dummy text of the printing.", "Credit", "10 Nov 2019","540$", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
            [ "7", "Hospital Memo", "Lorem Ipsum is simply dummy text of the printing.", "Debit", "16 Nov 2019","300$", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
            [ "8", "Hospital Memo", "Lorem Ipsum is simply dummy text of the printing.", "Credit", "12 Nov 2019","1340$", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
            [ "9", "Hospital Memo", "Lorem Ipsum is simply dummy text of the printing.", "Credit", "8 Nov 2019","640$", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
            [ "10", "Hospital Memo", "Lorem Ipsum is simply dummy text of the printing.", "Debit", "15 Nov 2019","1240$", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
            [ "11", "Hospital Memo", "Lorem Ipsum is simply dummy text of the printing.", "Credit", "16 Nov 2019","940$",  "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
            [ "12", "Hospital Memo", "Lorem Ipsum is simply dummy text of the printing.", "Debit", "5 Nov 2019","1230$", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
            [ "13", "Hospital Memo", "Lorem Ipsum is simply dummy text of the printing.", "Debit", "15 Nov 2019","2330$", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
            [ "14", "Hospital Memo", "Lorem Ipsum is simply dummy text of the printing.", "Debit", "5 Nov 2019","2210$", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],

          ];


          var dataSet5 = [
              [ "1", "Jacob" , "NON-AC-1st Floor","Lorem Ipsum is simply dummy text of the printing.","1/2",    "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
              [ "2", "Nick" , "AC-3rd Floor","Lorem Ipsum is simply dummy text of the printing.","1/2", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
              [ "3", "Richard" , "NON-AC-2nd Floor","Lorem Ipsum is simply dummy text of the printing.","1/2",  "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
              [ "4", "Kyle" , "AC-2nd Floor","Lorem Ipsum is simply dummy text of the printing.","1/2",    "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
              [ "5", "jennifer" , "NON-AC-3rd Floor","Lorem Ipsum is simply dummy text of the printing.","1/2","<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
              [ "6", "Adwerd" , "AC-1st Floor","Lorem Ipsum is simply dummy text of the printing.","1/2",   "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
              [ "7", "Rose" , "NON-AC-2nd Floor","Lorem Ipsum is simply dummy text of the printing.","1/2",  "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
              [ "8", "Joshphe" , "AC-2nd Floor","Lorem Ipsum is simply dummy text of the printing.","1/2",    "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
              [ "9", "Nelson" , "NON-AC-2nd Floor","Lorem Ipsum is simply dummy text of the printing.","1/2",   "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
              [ "10", "Jacob" , "AC-2nd Floor","Lorem Ipsum is simply dummy text of the printing.","1/2",   "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
              [ "11", "Nick" , "AC-2nd Floor","Lorem Ipsum is simply dummy text of the printing.","1/2",   "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
              [ "12", "Richard" ,"AC-3rd Floor" ,"Lorem Ipsum is simply dummy text of the printing.","1/2",  "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
              [ "13", "Kyle" , "NON-AC-1st Floor","Lorem Ipsum is simply dummy text of the printing.","1/2",   "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
              [ "14", "jennifer" ,"AC-2nd Floor","Lorem Ipsum is simply dummy text of the printing.","1/2",   "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a> <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],

            ];



                    var dataSet6 = [
                        [ "1", "Summer Solstice 2020 (Southern Hemisphere)", "Lorem Ipsum is simply dummy text of the printing.", "16 Dec 2019", "19 Dec 2019","Joshua", "<span class='badge badge-outline-success'>Active</span>" ,"<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                        [ "2", "Summer Solstice 2020 (Southern Hemisphere)", "Lorem Ipsum is simply dummy text of the printing.", "09 Dec 2019", "15 Dec 2019", "Denise", "<span class='badge badge-outline-danger'>Inactive</span>"  , "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                        [ "3", "Summer Solstice 2020 (Southern Hemisphere)", "Lorem Ipsum is simply dummy text of the printing.", "18 Dec 2019", "22 Dec 2019", "Jennifer", "<span class='badge badge-outline-success'>Active</span>"  , "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                        [ "4", "Summer Solstice 2020 (Southern Hemisphere)", "Lorem Ipsum is simply dummy text of the printing.", "16 Dec 2019", "19 Dec 2019", "Kyle", "<span class='badge badge-outline-danger'>Inactive</span>"  , "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                        [ "5", "Summer Solstice 2020 (Southern Hemisphere)", "Lorem Ipsum is simply dummy text of the printing.", "01 Dec 2019", "8 Dec2019", "Judy", "<span class='badge badge-outline-success'>Active</span>"  , "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                        [ "6", "Summer Solstice 2020 (Southern Hemisphere)", "Lorem Ipsum is simply dummy text of the printing.", "18 Dec 2019", "22 Dec 2019", "Gavin",  "<span class='badge badge-outline-danger'>Inactive</span>" , "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                        [ "7", "Summer Solstice 2020 (Southern Hemisphere)", "Lorem Ipsum is simply dummy text of the printing.", "16 Dec 2019", "19 Dec 2019", "Henry", "<span class='badge badge-outline-success'>Active</span>"  , "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                        [ "8", "Summer Solstice 2020 (Southern Hemisphere)", "Lorem Ipsum is simply dummy text of the printing.", "18 Dec 2019", "22 Dec 2019", "Judy",  "<span class='badge badge-outline-danger'>Inactive</span>" , "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                        [ "9", "Summer Solstice 2020 (Southern Hemisphere)", "Lorem Ipsum is simply dummy text of the printing.", "18 Dec 2019", "22 Dec2019", "Gavin", "<span class='badge badge-outline-danger'>Inactive</span>"  , "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                        [ "10", "Summer Solstice 2020 (Southern Hemisphere)", "Lorem Ipsum is simply dummy text of the printing.", "01 Dec 2019", "8 Dec 2019", "Henry", "<span class='badge badge-outline-success'>Active</span>"  , "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                        [ "11", "Summer Solstice 2020 (Southern Hemisphere)", "Lorem Ipsum is simply dummy text of the printing.", "18 Dec 2019", "22 Dec 2019", "Joshua", "<span class='badge badge-outline-danger'>Inactive</span>"  ,  "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                        [ "12", "Summer Solstice 2020 (Southern Hemisphere)", "Lorem Ipsum is simply dummy text of the printing.", "09 Dec 2019", "15 Dec2019", "Denise", "<span class='badge badge-outline-danger'>Inactive</span>"  , "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                        [ "13", "Summer Solstice 2020 (Southern Hemisphere)", "Lorem Ipsum is simply dummy text of the printing.", "01 Dec 2019", "8 Dec 2019", "Kyle",  "<span class='badge badge-outline-success'>Active</span>" , "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                        [ "14", "Summer Solstice 2020 (Southern Hemisphere)", "Lorem Ipsum is simply dummy text of the printing.", "09 Dec 2019", "15 Dec2019", "Jennifer", "<span class='badge badge-outline-danger'>Inactive</span>"  , "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],

                      ];

                      var dataSet7 = [
                          [ "1", "<img src='../../assets/img/employee-list/employee-(1).jpg'>", "Denise", "denise176@gmail.com", "New York", "	(836) 257 1340",  "10 Nov 2012", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                          [ "2", "<img src='../../assets/img/employee-list/employee-(3).jpg'>", "Jennifer", "jennifer@gmail.com", "Los Angeles", "	(836) 257 1379", "16 Nov 2014", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                          [ "3", "<img src='../../assets/img/employee-list/employee-(5).jpg'>", "Kyle", "kyle698@gmail.com", "Atlanta", "	(836) 257 1372",  "12 Nov 2015", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                          [ "4", "<img src='../../assets/img/employee-list/employee-(4).jpg'>", "Joshua", "joshua@gmail.com", "Chicago", "	(836) 257 1374",  "16 Nov 2009", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                          [ "5", "<img src='../../assets/img/employee-list/employee-(2).jpg'>", "Adwerd", "adwerd@gmail.com", "California", "	(836) 257 1371",  "8 Nov 2015", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                          [ "6", "<img src='../../assets/img/employee-list/employee-(6).jpg'>", "Daniel", "daniel983@gmail.com", "New York", "	(836) 257 1375", "10 Nov 2009", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                          [ "7", "<img src='../../assets/img/employee-list/employee-(7).jpg'>", "Jennifer", "jennifer@gmail.com", "San Francisco", "	(836) 257 1373",  "16 Nov 2019", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                          [ "8", "<img src='../../assets/img/employee-list/employee-(8).jpg'>", "Kyle", "kyle698@gmail.com", "Los Angeles", "	(836) 257 1312", "12 Nov 2014", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                          [ "9", "<img src='../../assets/img/employee-list/employee-(1).jpg'>", "Joshua", "joshua@gmail.com", "Atlanta", "	(836) 257 1332",  "8 Nov 2009", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                          [ "10", "<img src='../../assets/img/employee-list/employee-(2).jpg'>", "Adwerd", "adwerd@gmail.com", "California",  "	(836) 257 1324", "15 Nov 2015", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                          [ "11", "<img src='../../assets/img/employee-list/employee-(7).jpg'>", "Daniel", "daniel983@gmail.com", "Chicago", "	(836) 257 1343",  "16 Nov 2017",  "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                          [ "12", "<img src='../../assets/img/employee-list/employee-(4).jpg'>", "Henry", "henry@gmail.com", "San Francisco", "	(836) 257 1354",  "5 Nov 2010", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                          [ "13", "<img src='../../assets/img/employee-list/employee-(8).jpg'>", "Jacob", "jacob@gmail.com", "New York", "	(836) 257 1325",  "15 Nov 2017", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                          [ "14", "<img src='../../assets/img/employee-list/employee-(6).jpg'>", "Gavin", "gavin@gmail.com", "Atlanta", "	(836) 257 1131", "5 Nov 2010", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                        ];




                        var dataSet8 = [
                            [ "1", "<img src='../../assets/img/nurse-list/nurse-1.jpg'>", "Bella", "bella176@gmail.com", "New York", "	(836) 257 1340",  "10 Nov 2012", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                            [ "2", "<img src='../../assets/img/nurse-list/nurse-2.jpg'>", "Jennifer", "jennifer@gmail.com", "Los Angeles", "	(836) 257 1379", "16 Nov 2014", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                            [ "3", "<img src='../../assets/img/nurse-list/nurse-3.jpg'>", "Kyle", "kyle698@gmail.com", "Atlanta", "	(836) 257 1372",  "12 Nov 2015", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                            [ "4", "<img src='../../assets/img/nurse-list/nurse-4.jpg'>", "Rose", "rose@gmail.com", "Chicago", "	(836) 257 1374",  "16 Nov 2009", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                            [ "5", "<img src='../../assets/img/nurse-list/nurse-5.jpg'>", "Abigail", "abigail@gmail.com", "California", "	(836) 257 1371",  "8 Nov 2015", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                            [ "6", "<img src='../../assets/img/nurse-list/nurse-6.jpg'>", "Aimee", "aimee983@gmail.com", "New York", "	(836) 257 1375", "10 Nov 2009", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                            [ "7", "<img src='../../assets/img/nurse-list/nurse-1.jpg'>", "Jennifer", "jennifer@gmail.com", "San Francisco", "	(836) 257 1373",  "16 Nov 2019", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                            [ "8", "<img src='../../assets/img/nurse-list/nurse-2.jpg'>", "Kyle", "kyle698@gmail.com", "Los Angeles", "	(836) 257 1312", "12 Nov 2014", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                            [ "9", "<img src='../../assets/img/nurse-list/nurse-3.jpg'>", "Edne", "edne@gmail.com", "Atlanta", "	(836) 257 1332",  "8 Nov 2009", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                            [ "10", "<img src='../../assets/img/nurse-list/nurse-4.jpg'>", "Ellena", "ellena@gmail.com", "California",  "	(836) 257 1324", "15 Nov 2015", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                            [ "11", "<img src='../../assets/img/nurse-list/nurse-5.jpg'>", "Elly", "elly983@gmail.com", "Chicago", "	(836) 257 1343",  "16 Nov 2017",  "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                            [ "12", "<img src='../../assets/img/nurse-list/nurse-6.jpg'>", "Gennie", "gennie@gmail.com", "San Francisco", "	(836) 257 1354",  "5 Nov 2010", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                            [ "13", "<img src='../../assets/img/nurse-list/nurse-1.jpg'>", "Suzanne", "suzanne@gmail.com", "New York", "	(836) 257 1325",  "15 Nov 2017", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                            [ "14", "<img src='../../assets/img/nurse-list/nurse-2.jpg'>", "Bella", "bella@gmail.com", "Atlanta", "	(836) 257 1131", "5 Nov 2010", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                          ];

                          var dataSet9 = [
                              [ "1", "<img src='../../assets/img/pharmacist-list/pharmacist-1.jpg'>", "Denise", "denise176@gmail.com", "New York", "	(836) 257 1340",  "10 Nov 2012", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                              [ "2", "<img src='../../assets/img/pharmacist-list/pharmacist-2.jpg'>", "Jennifer", "jennifer@gmail.com", "Los Angeles", "	(836) 257 1379", "16 Nov 2014", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                              [ "3", "<img src='../../assets/img/pharmacist-list/pharmacist-3.jpg'>", "Kyle", "kyle698@gmail.com", "Atlanta", "	(836) 257 1372",  "12 Nov 2015", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                              [ "4", "<img src='../../assets/img/pharmacist-list/pharmacist-4.jpg'>", "Joshua", "joshua@gmail.com", "Chicago", "	(836) 257 1374",  "16 Nov 2009", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                              [ "5", "<img src='../../assets/img/pharmacist-list/pharmacist-1.jpg'>", "Adwerd", "adwerd@gmail.com", "California", "	(836) 257 1371",  "8 Nov 2015", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                              [ "6", "<img src='../../assets/img/pharmacist-list/pharmacist-4.jpg'>", "Daniel", "daniel983@gmail.com", "New York", "	(836) 257 1375", "10 Nov 2009", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                              [ "7", "<img src='../../assets/img/pharmacist-list/pharmacist-1.jpg'>", "Jennifer", "jennifer@gmail.com", "San Francisco", "	(836) 257 1373",  "16 Nov 2019", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                              [ "8", "<img src='../../assets/img/pharmacist-list/pharmacist-4.jpg'>", "Kyle", "kyle698@gmail.com", "Los Angeles", "	(836) 257 1312", "12 Nov 2014", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                              [ "9", "<img src='../../assets/img/pharmacist-list/pharmacist-1.jpg'>", "Joshua", "joshua@gmail.com", "Atlanta", "	(836) 257 1332",  "8 Nov 2009", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                              [ "10", "<img src='../../assets/img/pharmacist-list/pharmacist-4.jpg'>", "Adwerd", "adwerd@gmail.com", "California",  "	(836) 257 1324", "15 Nov 2015", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                              [ "11", "<img src='../../assets/img/pharmacist-list/pharmacist-1.jpg'>", "Daniel", "daniel983@gmail.com", "Chicago", "	(836) 257 1343",  "16 Nov 2017",  "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                              [ "12", "<img src='../../assets/img/pharmacist-list/pharmacist-4.jpg'>", "Henry", "henry@gmail.com", "San Francisco", "	(836) 257 1354",  "5 Nov 2010", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                              [ "13", "<img src='../../assets/img/pharmacist-list/pharmacist-1.jpg'>", "Jacob", "jacob@gmail.com", "New York", "	(836) 257 1325",  "15 Nov 2017", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                              [ "14", "<img src='../../assets/img/pharmacist-list/pharmacist-4.jpg'>", "Gavin", "gavin@gmail.com", "Atlanta", "	(836) 257 1131", "5 Nov 2010", "<a href='#'><i class='fas fa-pencil-alt ms-text-primary'></i></a>   <a href='#'><i class='far fa-trash-alt ms-text-danger'></i></a>" ],
                            ];




  var tableOne = $('#data-table-1').DataTable( {
    data: dataSet,
    columns: [
      { title: "Name" },
      { title: "Position" },
      { title: "Office" },
      { title: "Extn." },
      { title: "Start date" },
      { title: "Salary" }
    ],
  });
  var tableTwo = $('#data-table-2').DataTable( {
    data: dataSet,
    columns: [
      { title: "Name" },
      { title: "Address" },
      { title: "Disease" },
      { title: "Age" },
      { title: "Phone" },
      { title: "Email" },
      { title: "Action" }
    ],
  });
  var tableThree = $('#data-table-3').DataTable( {
    data: dataSet,
    columns: [
      { title: "Name" },
      { title: "Position" },
      { title: "Office" },
      { title: "Extn." },
      { title: "Start date" },
      { title: "Salary" }
    ],

  });
  var tableFour = $('#data-table-4').DataTable( {
    data: dataSet,
    columns: [
      { title: "Name" },
      { title: "Position" },
      { title: "Office" },
      { title: "Extn." },
      { title: "Start date" },
      { title: "Salary" }
    ],
      });



  var tableFour = $('#data-table5').DataTable( {
    data: dataSet1,
    columns: [
      { title: "#" },
      { title: "Doctor Name" },
      { title: "Department Name" },
      { title: "Department Head" },
      { title: "Status" },
      { title: "Action" },
    ],
      });

      var tableFour = $('#data-table6').DataTable( {
        data: dataSet2,
        columns: [
          { title: "Serial No." },
          { title: "Doctor Name" },
          { title: "Department Name" },
          { title: "Day" },
          { title: "Start time" },
          { title: "End time" },
          { title: "Status" },
          { title: "Update" }
        ],
          });


          var tableFour = $('#data-table7').DataTable( {
            data: dataSet3,
            columns: [
              { title: "ID" },
              { title: "Patient Name" },
              { title: "Age" },
              { title: "Doctor Name" },
              { title: "Department" },
              { title: "Date" },
              { title: "Time" },
              { title: "Disease" },
              { title: "Action" }
            ],
              });

              var tableFour = $('#data-table8').DataTable( {
                data: dataSet4,
                columns: [
                  { title: "Serial No." },
                  { title: "Account Name" },
                  { title: "Description" },
                  { title: "Type" },
                  { title: "Date" },
                  { title: "Amount" },
                  { title: "Action" },

                ],
                  });

                  var tableFour = $('#data-table9').DataTable( {
                    data: dataSet5,
                    columns: [
                      { title: "Serial No." },
                      { title: "Patient Name" },
                      { title: "Bed Type" },
                      { title: "Description" },
                      { title: "Bed Capacity" },

                      { title: "Update" },

                    ],
                      });


                      var tableFour = $('#data-table10').DataTable( {
                        data: dataSet6,
                        columns: [
                          { title: "#" },
                          { title: "Title" },
                          { title: "Description" },
                          { title: "Start Date" },
                          { title: "End Date" },
                          { title: "Assign By" },
                          { title: "Status" },
                          { title: "Action" }

                        ],
                          });

                          var tableFour = $('#data-table11').DataTable( {
                            data: dataSet7,
                            columns: [
                              { title: "S.No." },
                              { title: "Pic" },
                              { title: "Name" },
                              { title: "Email" },
                              { title: "Address" },
                              { title: "Mobile" },
                              { title: "Joining Date" },
                              { title: "Update" }

                            ],
                              });

                              var tableFour = $('#data-table12').DataTable( {
                                data: dataSet8,
                                columns: [
                                  { title: "S.No." },
                                  { title: "Pic" },
                                  { title: "Name" },
                                  { title: "Email" },
                                  { title: "Address" },
                                  { title: "Mobile" },
                                  { title: "Joining Date" },
                                  { title: "Update" }

                                ],
                                  });


                                  var tableFour = $('#data-table13').DataTable( {
                                    data: dataSet9,
                                    columns: [
                                      { title: "S.No." },
                                      { title: "Pic" },
                                      { title: "Name" },
                                      { title: "Email" },
                                      { title: "Address" },
                                      { title: "Mobile" },
                                      { title: "Joining Date" },
                                      { title: "Update" }

                                    ],
                                      });
})(jQuery);
