-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: just_instruments
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `name` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` text,
  `address` tinytext,
  `phone` varchar(50) DEFAULT NULL,
  `fax` varchar(50) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=627 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES ('Westons Foods Cobourg','Marilyn Grove','740 Division Street, Northam Industrial Park, Building #1 West\nCobourg Ontario K9A4R5\nCanada, ,','905-373-7089 x 232,905-373-9090','','Marilyn.Grove@westonfoods.com',154),('Will Pick-up','',', ,','','','',155),('YMCA of Greater Toronto','Joshua Cherian','20 Union Street\nBrampton ON L6V 1R2\nCanada, ,','905 451 1400 x 430,416-560-5969','','joshua.cherian@ymcagta.org',156),('Weston Foods- Gluten Free Facility','Amita Kanwar','122 Carrier Drive\nToronto Ontario M9N2G9\nCanada, ,','416-656-2828 x1111,\n647-852-2363','416-656-8383','Amita.Kanwar@WestonFoods.com, karim.dinani@westonfoods.com',153),('Weston Foods - Nashua Drive','Yingchen Su','3880 Nashua Drive\nMississauga Ontario L4V 1M5\nCanada, ,','905-612-1958  e: 241,647-608-7368','','ying.chensu@westonfoods.com, Max.Kumar@WestonFoods.com, Nupur.Gera@WestonFoods.com',151),('Weston Foods, Concord','Farhan Hamid','273 Edgeley Blvd\nConcord ON L4K 3Y7\nCanada, ,','905-660-4310\n,647-379-4993','','Farhan.Hamid@WestonFoods.com',152),('Weston Bakeries Limited Carrier Drive','Amita Kanwar','122 Carrier Drive\nEtobicoke Ontario M9W5R1\nCanada, ,','416-674-4472,647-971-9236','','amita.kanwar@westonbakeries.com, Adina.Stefanov@WestonBakeries.com',149),('Weston Foods (Canada) Inc. Annette\'s Donuts','','Accounts Payable Department\n2095 Medowvale Blvd.\nMississauga Ontario L5N5N1\nCanada, ,','905-903-6108','','AP.Maintenance@westonbakeries.com',150),('Wesco Distribution Canada LP','Jose Remulla','6170 Belgrave Rd.\nMississauga ON L5R 4G8\nCanada, ,','905-755-1914,\n416-417-2130','','jremulla@wesco.com',148),('Villar Group Inc. (Villar Foods)','Al Villar','120 Whitmore Rd., UNIT 10-12\nWoodbridge ON L4L 6A4\nCanada, ,','905-264-9191,905-965-5060','','al@villargroupinc.com',146),('Weldo Canada','Mike Maruwala','1830 Gage Crt, Unit C\nMississauga ON L5S 1S2\nCanada, ,','905-677-7800,905-602-7779','','weldocanada@gmail.com',147),('Tubefit Inc.','','260 Galaxy Blvd.\nToronto Ontario M9W5R8\nCanada, ,','416-675-9929','416-675-9939','',141),('Tutto Cheese Company Ltd','Poonam','11 Regan Rd\nBrampton ON L7A 1B2\nCanada, ,','647-708-5749,905-495-8555','','poonam@tuttocheese.com, martin@tuttocheese.com',142),('Tyne Engineering Inc','Dev Sivanesan','730 Darlene Ct, ,\nBurlington ON L7L 5V1\nCanada, ,','289-288-0490 x 225','','dev.s@tyne-engineering.com',143),('Urban Watershed Group Ltd.','Eric Persichini','Suite 304, 15955 Airport Road\nCaledon East Ontario L7C1H9\nCanada, ,','905-584-1458\n,905-730-2311','905-584-1461','epersichini@grnland.com',144),('Trin Calservices Inc','Steve Ramsumair','45 Bramalea Rd. Unit 204\nBrampton ON L6T2W4\nCanada, ,','905-794-1878,416-268-7585','','steve.ramsumair@trincalservices.com, accounting@trincalservices.com',140),('Varinder Puarr','Varinder Puarr','82 Midsummer Dr.\nBrampton On L6P 3E4, ,','416-558-3487','','',145),('Treasure Mills Inc.','Constant Ma','90 Engelhard Drive\nAurora ON L4G 3V2\nCanada, ,','905-503-6455','905-503-5995','qa@treasuremills.com',139),('The Green Organic Dutchman Holdings Ltd.','Siva Sompalli​','1915 Jerseyville Road W\nAncaster ON L1R0R1\nCanada, ,','905-304-4201','','ssompalli@tgod.ca',136),('Toromont CAT','Chris Hughson','25 Mumford Rd\nLively ON P3Y 1K9\nCanada, ,','705-688-4581','','chughson@toromont.com',138),('Tiffany Gate Foods Inc.','','195 Stelnway Blvd.\nToronto Ontario M9W6H6\nCanada, ,','4162139720 x296\n','416-213-9790','tracey.strachan@tiffanygate.com',137),('The Board of Governers of Exhibition place','Marius Dragu','Direct Energy Centre, 100 Princess Blvd., Suite 1\nToronto Ontario M6K3C3\nCanada, ,','416-407-0811,416-263-5219','416-263-3681','mdragu@explace.on.ca, JFurtado@Explace.on.ca',135),('Specialty Health Network - Shoppers Drug Mart','Naera Fatema','1685 Tech Avenue\nMississauga Ontario L4W 0A7\nCanada, ,','905-212-3800 EXT 3963,1-866-222-3312 EXT 3963','','Naera.Fatema@sdmshn.ca',130),('Stanley Engineered Fastening','Mike Sumandl','1030 Lorimar Drive\nMississauga ON L5S 1R8\nCanada, ,','905-364-0665,647-215-7431','','mike.sumandl@sbdinc.com',131),('Stephano Group Ltd.','Victor Khurumov','123 Eastside Drive, Unit 01\nEtobicoke ON M8Z 5S5\nCanada, ,','416-667-0707','','victor.k@stephanogroup.ca',132),('Sunny Crunch Foods','','200 Shields Court\nMarkham Ontario L3R9T5\nCanada, ,','905-475-0422','905-475-9775','maintenance@sunnycrunch.com, kenlin@sunnycrunch.com',133),('The Bake House','Amy','250 Cochrane Drive, Unit 9\nMarkham ON L3R 8E5\nCanada, ,','905-604-8849','','info.thebakehousetoronto@gmail.com',134),('Sigan Industries Group','','296 Orenda Road West\nBrampton Ontario L6T4X6\nCanada, ,','9.05456888E8','905-456-8808','Preeti@siganindustries.com, Vicki@siganindustries.com',126),('Single Buoy Mooring Inc.','','5 RTE DE FRIBOURG\nMarly Switzerland P.O. Box 152, ,','41264399920\n','4.1264399939E10','',127),('SodaStream Canada Ltd.','Jacqueline Peryer-Reid','325B Annagem Blvd.\nMississauga Ontario L5T 3A7\nCanada, ,','416-317-6617','','jreid@sodastream.com',128),('Sons Bakery','Roy Aquino','8 Atlas Court\nBrampton ON L6T 5C1\nCanada, ,','905-458-0388,416-272-5109','','roya@sonsbakery.ca, dfrunchak@sonsbakery.ca, vipinc@sonsbakery.ca',129),('Service Pro Mechanical Inc.','Victoria','5792 Atlantic Drive, Unit 5\nMississauga Ontario L4W 4N8\nCanada, ,','(905) 670 - 6868,(416) 717 - 2318','(905) 670 - 4507','office@servicepromechanical.ca',125),('Septa Pharmaceauticals Inc.','Meera Rajendran','7490 Pacific Circle, Unit # 1\nMississauga ON L5T 2A3\nCanada, ,','9055645665 x 230,647-968-1841','','meera@septapharmaceuticals.com',124),('Select Plumbing & Heating Inc.','Marshall','259 Traders Blvd. East, Unit #5\nMississauga Ontario L4Z 4G5\nCanada, ,','647-444-5501','','',123),('Savaria','Bhumit Mistri','2 Walker Drive\nBrampton ON L6T 5E1\nCanada, ,','800-265-5416','','bmistri@savaria.com',122),('Riverwood Management','Frank Giraldi','32 Victor Ave , lower level\nEtobicoke Ontario M8V 4L9\nCanada, ,','','','frank@riverwoodmgmt.ca',116),('Rotech Pumps & Systems Inc.','Abhijeet Ashtikar','1-1180 Britannia Road east\nMississauga Ontario L4W 3X1\nCanada, ,','905-461-9617,289-327-2647','','sales@rotechpumps.com',117),('Runnalls Industries Inc.','Dimitriy Zolkin','1275 Cardiff Blvd.,\nMississauga Ontario L5S 1R1\nCanada, ,','905-453-4220 Ext 229','','dima@runnalls.com',118),('S.C. Johnson','','1 Webster St\nBrantford ON N3T 5R1\nCanada, ,','519-758-6555 ext 87425','','LMacken@scj.com, dotlews@scj.com',119),('S.R. Precision Technologies Inc.','','420 Main St. E, Suite# 859\nMilton ON L9T 5G3\nCanada, ,','905-782-2426','','sales@srptek.com',120),('Sativa Consultation','Marc D andreis','772 Ferguson Drive\nMilton On L9T7C8, ,','647-470-4936','','marc.dandreis@gmail.com',121),('Rex Pak Food Packaging Ltd.','Adina Stefanov','85 Thornmount Drive\nScarborough ON M1B 5V3\nCanada, ,','416-755-3324,\n416-755-3324 x208','','Adina@rexpak.com',115),('Ravi Mainh','Ravi Mainh','420 Main Stret E, Suite 859\nMilton On L9T 5G3\nCanada, ,','905-782-2426,905-203-2818','','sales@srptek.com',113),('Refresco North America','Tim Thom','6525, Viscount Road\nMississauga ON L4V 1H6\nCanada, ,','905-672-1900x119540,\n416-220-8466','','tim.thom@refresco.com, liliana.rautu@refresco.com, APInvoices.NA@Refresco.com',114),('Qualtech Inc.','Terry Kenny','1880, Leon-Harmel\nQuebec G1N4K3\nCanada, ,','418-686-3802','418-686-3808','Terry.Kenny@Qualtech.ca',111),('Rapid Aid Corp.','Dhrumil Shah','4120A Sladeview Crescent\nMississauga ON L5L 5Z3\nCanada, ,','905.820.4788 Ext. 235','905-820-9226','dshah@rapidaid.com',112),('Power Bus Way Ltd.','Jackie','5 Strathearn Ave\nBrampton ON L6T 4P1\nCanada, ,','905-792-9333\n,877-877-2091','','jhart@powerbusway.com',110),('POM Plumbing Inc.','Leon Glazer','3045 Southcreek Road. Unit 30\nMississauga ON L4X 2X7\nCanada, ,','905-625-8777,647-296-7878','','lglazer@pomplumbing.ca',108),('Portuguese Cheese Company','Olena Lunyova','2 Buckingham Street\nToronto ON M8Y 2W1\nCanada, ,','416-259-4349,\n','416-259-8922','qa@portuguesecheese.com',109),('Paterson Composites','Gordon Cook','20 Fasken Dr\nToronto ON M9W 1K5\nCanada, ,','416-521-9999,416-209-0115','','gordon.cook@patersoncomposites.ca',104),('Pepsico Beverages Company','Tim Sripathmarajah','5900 Falbourne Street MISSISSAUGA ONTARIO L6R3M2 CANADA','905-568-7748,416-989-2435, 416-505-9832','905-599-2361','timothy.sripathmarajah@pepsico.com',106),('Polarpak Limited','Jay Makuni','26 Victoria Cres\nBrampton ON L6T 1E5\nCanada, ,','905-792-3000,416-562-6938','','Jayachandran.Makuni@novolex.com, Rajesh.Patel@novolex.com, AP-PolarpakBrampton@novolex.com',107),('Ositech Communication Inc.','Nok Maneekraisorn','430 Laird Road, Unit 5-7\nGuleph ON N1G 3X7\nCanada, ,','519-836-8063','','thippawanm@ositech.com',102),('Palma Pasta','Baharak Dadman','3475 Semenyk Court\nMississauga Ontario L5C 4P0\nCanada, ,','905-896-9000\n','905-896-1987','ross@palmapasta.com, baharak.dadman@gmail.com',103),('Ontario Impex of Canada Inc','Vivek Verma','190 Statesman Dr\nMississauga ON L5S 1X7\nCanada, ,','905-502-7277','','vivek.verma@oicfoods.com',101),('OLS-NA Inc.','Abby Niu','2 Cochran Drive\nAyr ON N0B 1E0\nCanada, ,','519-621-4563 ext 104','','abbyn@ols-na.com, davem@ols-na.com',100),('Olde York Potato Chips','Fazir Buckreedan','230 Deerhurst Drive\nBrampton Ontario L6T 5R8\nCanada, ,','9054584100 Ext. 25\n','905-458-1780','maintenance@oldeyorkchips.com, fbuckreedan@gmail.com',99),('Ocean Wave Imports','Ashwin Lachman','6295 Northam Dr Unit 14\nMississauga ON L4V 1H8\nCanada, ,','905-672-5050,647-289-3476','','ashwin@oceanwaveimports.ca',98),('Nova Cold Logistics- Brampton','Mr. Anand Deonarine','745 Intermodal Drive\nBrampton Ontario L6T 5W2\nCanada, ,','416-948-0078','','anand.deonarine@novacold.com, jaswinder.saini@novacold.com, hetal.prajapati@novacold.com',94),('NuStef Baking Ltd. (Nustef Foods)','Pauline Woo','2440 Cawthra Road\nMississauga ON L5A 2X1\nCanada, ,','905-896-3060\n','905-896-4349','pwoo@nustefbaking.com',95),('Nutra Sprout Wholesale Inc.','Fang Zhu','7570 Torbram Road, Unit A.\nMississauga ON L4T 3L8\nCanada, ,','905-678-8388, 647-801-8999','905-678-8288','fangzhu@nutrasprout.com',96),('Nutral Lab. Corporation','Lance Li','980 TAPSCOTT RD.\nTORONTO ON M1X 1C3\nCANADA, ,','905-752-1823,866-446-6766','','lance.l@NUTRALABCORP.COM',97),('N&K Industrial Maintenance Inc.','','7385 Torbram Road, Unit #3\nMississauga Ontario L4T3Y9\nCanada, ,','905-677-0175\n','905-672-5630','',89),('Nalco Aluminium Company Limited','Suryakant Mohanty','Nalco Aluminium Company Limited\nAngul Odisha 759145\nIndia, ,','(+91) 94379 66100\n','(+91)0674-2300246','suryakant.mohanty@nalcoindia.co.in',90),('Nature\'s Own Cosmetic Company - Private Label','Ikjinder Singh','80 Penn Dr\nNorth York ON M9L 2A9\nCanada, ,','416-661-8111','','quality@jordane.com',91),('Nestle Waters NA','','Nestle Waters NA\nPO Box 5917\nTROY MI 48007-5917\nUSA, ,','519-767-6824,519-362-1885','','John.Mccarthy@waters.nestle.com, Lori.Smith@waters.nestle.com, Dhaval.Barot@waters.nestle.com',92),('Nova Cold Logistics Calgary','Krupal Patwa','10401 - 46th Street SE\nCalgary Alberta T2C2X9\nCanada, ,','403-437-4899','','Krupal.patwa@novacold.com',93),('Merkel Air Systems Ltd.','Rob Merkel','8 Densmore Avenue\nEtobicoke ON M9W1V2\nCanada, ,','416-606-7467','','merkelairsystems@gmail.com',84),('Metropolitan Ice Cream','','26 Milford Ave.\nNorth York Ontario M6M2V8\nCanada, ,','416-245-1335','416-245-3152','mic@bellnet.ca',85),('Modhani Inc.','','21 Reagan Road, Unit(s) # F & G\nBrampton Ontario L7A1C5\nCanada, ,','905-495-3842\n','905-495-4612','barinder@modhani.ca, vsehdev@modhani.ca',86),('Mohamed Dhooma','Mohamed Dhooma','211-30 Tullamore Rd\nBrampton ON L6W 1J9\nCanada, ,','905-320-8527','','mduma1@gmail.com',87),('Monforte Dairy','Daniel','49 Griffith Rd\nStratford ON N5A 6S4\nCanada, ,','519-814-7920','','monfortedairyorders@gmail.com',88),('Mediterranean Dairy','Saad (Steve) Aloush','100 Rutgherford Rd South., Unit 1A\nBrampton ON L6W 3J5\nCanada, ,','416-716-9488','','samorkabusiness@gmail.com, saadallouch@hotmail.com',83),('Mark Saldanha','Mark Saldanha','48 newport st.\nBrampton ON L6S 4T1\nCanada, ,','647-991-7460','','markjs92@gmail.com',82),('Maplehurst Bakery Inc. - Brampton','Jamie Smith','379 Orenda Road\nBrampton Ontario L6T1G6\nCanada, ,','905-903-4252','905-791-7400','Jerias.Pablo@WestonFoods.com, Jamie.Smith@WestonFoods.com',81),('Maplehurst Bakeries Inc. - North York','','675 Fenmar Drive\nNorth York Ontario M9L1C8\nCanada, ,','416-741-1620 x241,\n437-333-8475','416-741-5104','Harut.Navasardyan@WestonFoods.com, volodymyr.buben@WestonFoods.com, Danish.Iqbal@WestonFoods.com',80),('Maplehurst (Annette\'s Donuts)','Val Podymov','1965 Lawrence Avenue West\nToronto Ontario M9N 1H5\nCanada, ,','4166563444 ex.202, 416-573-9394','','val.podymov@maplehurstbakeries.com, aimin.Qiu@westonfoods.com',78),('Maplehurst Bakeries - Mississauga','Jasper Manio','2095, Meadowvale Blvd.\nMississauga Ontario L5N 5N1\nCanada, ,','9055670660 x 4681','','jasper.manio@readybake.com, anshita.garg@readybake.com',79),('Mampster Inc','Manpreet Singh','B37-80 Nashdene Rd\nScarborough Ontario M1V 5E4\nCanada, ,','416-543-6727','','manpreet@mampster.com',77),('Likro Precision Ltd.','Mike Lawrie','3150 Pepper Mill Court\nMississauga ON L5L 4X4\nCanada, ,','905-828-9191','','m.lawrie@likro.com',75),('Magnum Integrated Technologies Inc.','Rakesh Arora','200 First Gulf Blvd.\nBrampton Ontario L6W4T5\nCanada, ,','9055951998 x61428','','jon.feliciano@mit-world.com',76),('Kye Lee','Kye Lee','131 Eddystone Av\nNorth York ON M3N 1H5\nCanada, ,','647-781-0650','','kyeheelee@gmail.com',74),('Kromet International Inc.','Lesley Inker','200 Sheldon Drive\nCambridge Ontario N1R 7K1\nCanada, ,','519-623-2511','519-624-9729','lesley.inker@kromet.com, nancy.norton@kromet.com',73),('Ideal Logistics Group','Tony Chand','6600 Goreway Dr. Unit E\nMississauga Ontario L4V 1S6\nCanada, ,','416-561-1860,\n905-636-7697','905-636-7801','tony@shipideal.com',68),('Ireks North America Ltd.','Veselinka Bijedic','9 Tracey Blvd.\nBrampton Ontario L6T5V6\nCanada, ,','905-789-9999','905-789-0233','veselinka.bijedic@ireks.com',69),('Karmen Truck Tire Centre Inc.','Karmen','286 Rutherford Road South,Unit 1A\nBrampton ON L6W 3K7\nCanada, ,','905-459-1000','905-459-1005','karmentrucktire@gmail.com',70),('Kings Mother Inc.','Ms Yuki','5-145 Industrial Parkway S.\nAurora Ontario L4G3V5\nCanada, ,','905-503-6886','905-503-6889','qa@kingsmother.com',71),('KL OIL & GAS, LLC','Michelle Watson','185 BRANHAM LANE\nSAN JOSE CA 95136\nUSA, ,','408-622-9939','408-416-0322','michelle@kloilngas.com',72),('Hydra-Fab Fluid Power Inc.','James Grace','3585 Laird Rd., Unit 5\nMississauga Ontario L5L5Z8\nCanada, ,','905-569-1819 x240\n','905-569-7801','jgrace@hydrafab.com, insidesales@hydrafab.com',67),('Henry Li','Henry Li','40 Darling Crescent\nGuelph ON\nCanada, ,','226-343-1149','','henry.li@dawnwatersolutions.com',66),('Heidenhain Corporation (Canada)','Paul Most','11-335 Admiral Blvd.\nMississauga Ontario L5T 2N2\nCanada, ,','905-670-8900\n','905-670-4426','pmost@heidenhain.com',65),('H & A Health Canada Inc.','Ryan','180 Brodie Drive, Unit 3\nRICHMOND HILL ON L4B3K8\nCANADA, ,','905-237-7898','','ryan@hacanada.com',63),('Hans Dairy Inc.','','3400 American Drive\nMississauga Ontario L4V1C1\nCanada, ,','905-671-3200\n','905-671-3205','shans@hansdairy.com',64),('Grenhall Industries Inc.','Gordon Halpern','1 Imperial Court\nBrampton ON L6T 5A8\nCanada, ,','905-458-8549\n','905-458-8363','gdhalpern@grenhall.com',62),('GP Custom Metals Inc.','James Dinkha','1241 Kerrisdale Blvd.\nNewmarket Ontario L3Y 8W1\nCanada, ,','905-898-8088,647-773-3915','905-898-8588','jamesd@gpcustom.ca',61),('Golder Associates Ltd.','Dany Giroux','1931 Robertson rd.\nNepean ON K2H 5B7\nCanada, ,','613-880-4604','','Dany_Giroux@golder.com',60),('Global Stucco Inc.','Sahib Cheema',', ,','416-564-1103','','',58),('Golden Boy Foods','William Gallego','70 West drive\nBrampton ON L6T 3T1\nCanada, ,','289-948-0611,647-633-1635','','william.gallego@8ave.com',59),('Give & Go','Alfaz Zaban','15 Marmac Drive, Unit 200\nToronto ON M9W 1E7\nCanada, ,','416.675.0114x2396,647-221-0856','','azaban@giveandgo.com',57),('Food Safety First Inc.','Grecia','55 Village Centre Court, Unit 200\nMississauga ON L4Z1S2\nCanada, ,','905-497-4435,647-970-4857','','grecia@foodsafetyfirstinc.ca, prabhjot@foodsafetyfirstinc.ca',54),('Fresh Selections','James Zhang','350 Creditstone Road, Unit 103\nConcord Ontario L4K 3Z2\nCanada, ,','905-760-0000 (480)\n','905-760-1895','james.zhang@freshselections.ca',55),('Gala Precision Engineering Pvt. Ltd.','Anand Kaundanya','A-59, Road No. 10, Eagle Industrial Estate\nThane Maharashtra 400 60\nIndia, ,','91-9820100258','','anand.kaundanya@galagroup.com, v.chandrasekharan@galagroup.com',56),('First International Courier Systems Inc.','Jeremy Waring','33 International Boulevard\nToronto ON M9W 6H3\nCanada, ,','800-387-3896\n,416-968-2000','416-674-5382','jeremy@internationalcourier.com',53),('EMF Electricals','','2690, Slough Street\nMississauga Ontario L4T1G3\nCanada, ,','905-405-8836','','',52),('Eltor Electric','Sergio Santos','1 Delta Park Blvd., Unit 13\nBrampton ON L6T 5G1\nCanada, ,','647-856-4563','','sergio.s@eltorelectric.ca',51),('Donut Time','Rommel Jitman','280 Rayette Rd\nConcord Ontario L4K 2G1\nCanada, ,','9057600850 x 501,416-837-0547','905-760-0849','rommel.jitman@donuttime.com, plantmanager@donuttime.com, qc@donuttime.com',46),('DUFFIN CREEK  W.P.C.P., REGION OF DURHAM','','Expenditure Management\n605 Rossland Road East,\nPO BOX 623\nWHITBY ONTARIO L1N6A3\nCANADA, ,','905-903-5880','905-686-3956','Biren.Shah@Durham.ca, em_invoices@durham.ca',47),('Dunn Rite Fire Protection Inc.','Chuck Dunn','1111 Davis Drive Unit #1, Suite 177\nNewmarket Ontario L3Y 9E5\nCanada, ,','905-953-6878\n','905-473-9786','',48),('East India Company Ltd.','Sapna Jain','2446 Cawthra Road, Unit 1, Bldg. 1\nMississauga ON L5A 3K6\nCanada, ,','905-276-3212\n','905-276-9793','sapna@eastindia.ca',49),('Electrovaya Corp.','Ling Zhang','6688 Kitimat Road\nMississauga ON L5N 1P8\nCanada, ,','905.855.4610 x 4617,905-855-4617','905-822-7953','lzhang@electrovaya.com, ap@electrovaya.com',50),('Dimpflmeier Bakery','Mr. Parveen Sondhi','26 Advance Road\nEtobicoke Ontario M8Z2T4\nCanada, ,','4162393031 Ex: 227\n','416-236-2701','parveen@dimpflmeierbakery.com',43),('Direct Plastic,  Novolex company','Andrew Murray','20 Stewart Ct\nOrangeville ON L9W 3Z9\nCanada, ,','519-942-8511 Ext. 252\n\n,519-939-9042','519-942-3979','Andrew.Murray@novolex.com',44),('Dock Edge + Inc','Sal','300 New Huntington Rd\nWoodbridge ON L4H 0R4\nCanada, ,','905-850-3999,416-779-7982','','ap@dockedge.com',45),('Dare Foods Limited Cambridge','Sara Kraguljac','25 Cherry Blossom Rd\n\n, Cambridge-ON ,N3H 4R7','519-893-3233','','skraguljac@darefoods.com',39),('Delmare Quality Foods Inc.','Hina Joshi','15 Meteor Dr.\n \n, Etobicoke-ON,M9W 1A3','416-233-5900','','hina@delmare.on.ca, lucy@delmare.on.ca',40),('Di-Pardo Foods Inc.','Cathy','71 Delta park Blvd., Unit 3\n, Brampton-ON,L6T5E7','905-790-1870','','dipardofoods@bellnet.ca',41),('DiaSYS Inc.','Ashvin Dhandha','501 Passmore Ave, Unit #2\nToronto ON M1V 5G4\nCanada, ,','416-292-3337,416-303-3684','416-292-3344','ashvin.dhandha@diasys.ca',42),('Dare Foods Limited','','2481 Kingsway Drive, Door # 33A\n \n, Kitchener-ON,N2C1A6','5198933233 x 3236\n','','apinvoice@darefoods.com',38),('CMS Ontario Limited','Lev','134 Norfinch Dr\n, North York-ON ,M3N1X7','416-736-4939\n','','levz@cmsfillings.com, imikhailova@cmsfillings.com',35),('Cosmo Heating and Cooling Inc.','Abdul Gaffoor','29 Maddybeth Crst.\n\n, Brampton-ON ,L6Y 5R7','416-471-4453','','magaffor@hotmail.com',36),('Cousin Plumbing & Mechanical','Dany Cousin','32 Alanadale Ave\n, Markham-ON,','647-869-7067','','danycousin@hotmail.com',37),('CJR Wholesale Grocers','Jhune David','5876 Coopers Ave\n \n, Mississauga-ON ,L4Z 2B9','647-921-5807','','jhune.d@cjrwholesale.com',34),('Caltec Process Control Instrumentation Ltd','Pradip Banerji','26 Valleyway Drive\n\n, Brampton-ON ,L6X5G3','416-420-4121\n','','pbanerjee@sympatico.ca',29),('Canada Drug Trading Corporation','Deepali Thomas','1801 Rutherford Rd. Unit 4 & 5\n \n, Vaughan-ON,L4K 5K6','647-237-5108','','deepali_thomas@yahoo.com',30),('Candiana Spirits (Eros Project Management)','Neeraj Bakshi','6033 Shawson Drive., Unit 9\n\n, Mississauga-ON ,L5T 1H8','416-726-1507','','info@canadianasp.com',31),('BN Progressive','Gurpreet Sodhi','1591, Trinity Drive\n\n, Mississauga-ON ,L5T 1K4','905-564-0063\n ','','gurpreet@bnprogressive.com, acc@bnprogressive.com',27),('City of Vaughan, Procurement Services Dept.','Jeremy Cayer','2141 Major Mackenzie Dr.\n\n, Vaughan-ON ,L6A 1T1','416-809-7725','','scott.wigmore@vaughan.ca, jeremy.cayer@vaughan.ca',33),('City of Cornwall, Engineering Services','Kyle McIntosh','\n1225 Ontario Street\n, Cornwall-ON ,K6H 4E1','(613) 930-2787 Ext: 2110\n\n','','jmarjerrison@cornwall.ca, kmcintosh@cornwall.ca, accpay@cornwall.ca',32),('Brickwater Construction Ltd.','Ryan Paiuk','606-53 Woodbridge Ave.\n\n, Woodbridge-ON,L4L 9K9','416-427-8080','','info@brickwater.ca',28),('Blackburn Plumbing','Giuseppe Virzi','11 Ivybridge Dr.\n\n, Brampton-ON ,L6V 2X1','647-321-1946','','',26),('Belterra Corporation','','35 Bury Crt\n\n, Brantford-ON ,N3S0A9','519-751-1240\n','','brantford@belterra.ca',25),('Bay Pharma Inc.','Nawsheen','155 Queen Street, Suite 205\n\n, Charlottetown-PEI ,C1A 4B4','902-569-3052','','baypharmainc@gmail.com',24),('ANVO PHARMA CANADA INC.','Mukesh Sareen','700 Third Line, Suite 111\n\n, Oakville-ON ,L6L 4B1','(905) 847 - 8666\n\n','','mukesh@anvopharma.com',20),('Assured Packaging Inc. , PLZ Aeroscience Corporation','Poornima Hosmath','300-2651 Warrenville Road. \n\n, Downers Grove IL,60515','905-565-1410 ext 30134','','phosmath@assuredpackaging.com, aorlowski@plzaeroscience.com, accountspayable@plzaeroscience.com',21),('Avery Human Resources Inc','Amrit Chahil','1100-201 City Centre Dr, Mississauga-ON ,L5B 2T4','(905) 896-6965 ext 128','','achahil@averyhr.com',22),('Avish Pharma Validating Service Inc.','','8 Grosbeak Crescent\n\n, Scarborough-ON ,M1X 1X1','647-894-4073\n','','avishvalidation@gmail.com',23),('Andrew Dickinson','Andrew Dickinson','25 Bethridge Road\n\n, Toronto-ON ,M9W 1M7','416-747-3976\n','','andrew.dickinson@shawcor.com',19),('Amir Quality Meats','Sherry Aziz','128 Hedgedale Rd.\n, Brampton-ON ,L6T 5L2','905-457-7557\n\n','','sherryaziz@amirqualitymeats.com',18),('AMG Metals Inc.','Mozafar Vafaei','21 Bales Dr W\n\n, East Gwillimbury-ON, L0G 1V0','905-953-4111 ext 247\n','','mozafar@amgmetals.com',17),('Alpha Pharmaceautical Laboratories','Masood Bhatti','1262 Don Mills Road\n\n, Toronto-ON ,M3B 2W7',' 416-449-2166','','m.bhatti@alphainc.org',16),('Aleafia Health Inc.','Constant Ma','2540 Shirley Road\n\n, Port Perry-ON ,L9L 1B3','647-395-6238','','constantma@AleafiaHealth.com',14),('Alpha Omega Management Corp','Aimin Qiu','514 Carlingview Dr\n \n, Etobicoke-ON,M9W 5R3','416-675-6660\n','','aimin.qiu@alphaomegamanagement.com',15),('Aerospace Mechanics Corporation','Villery','14 Strathearn Ave\n\n, Brampton-ON ,L6T 4P4','289-299-5200','','valeri.l@aerospacemechanics.com, elsa@aerospacemechanics.com',13),('Advanced Heat Treating Solutions','Rippen Anand','2459 Anson Drive\n\n, Mississauga-ON ,L5S 1G1','905-673-1611\n','','advhtreat@bellnet.ca',12),('Accuristix Advancing Healthcare Logistics','Vipul Patel','100 Vaughan Valley\n, \nVaughan-ON ,L4H 3C5','416-637-3273 Ext. 7031','','ViPatel@accuristix.com',7),('Ace Bakery Limited','Brian Sisson','580 Secretariat Court\n\n, Mississauga-ON ,L5S 2S5','905-565-8138\n','','bsisson@acebakery.com, rshah@acebakery.com',8),('Acrytec Panel Industries','Mike Brocca','40 Gaudaur Road, Unit 1\n\n, Woodbridge-ON, L4L 4S6','416-846-9357','','mbrocca@acrytecpanel.com',9),('ADM Dairy Inc','Devang Vaghela','15-5484 Tomken Road\n\n, Mississauga-ON , L4L 4S6','416-500-9560','','info.admdairy@gmail.com, mdv2002@gmail.com',10),('Advance Industrial Supplies','','173 Advance Blvd., Unit 33\n\n, Brampton-ON ,L6T4Z7','905-463-0038\n\n','','sales@advanceindustrial.net',11),('Access Machinery Inc.','Dan Tran','6260 Highway 7, Unit 7, Vaughan-ON ,L4H 4G3','905-265-9779','','dt@accessmachinery.ca',5),('Accurate Railroad Construction Ltd.','Nick Panza','130 Healey Road, Unit 8\n, \nCaledon-ON \n,L7E 5B3','905-951-8047\n','','nickpanza@accuraterailroad.com, brainmustard@accuraterailroad.com',6),('3L2R Inc.','Joanne Couturier','340 Sheldon Drive, Unit A\n\n, Cambridge-ON,N1T 1A9','519-629-0152','','jcouturier@3L2R.com',4),('13E13 INC.','Jujhar Bhamra','24 Woodvalley Dr.\n\n, Brampton-ON,l7A 1Z4','905-495-3312\n \n','','JUJHAR@13E13.CA',1),('21st Century Enviro Engineers Pvt. Ltd.','Raj Pal Midha','Vill.: Kunjhal, Baddi, Solan HP-India,','91- 97793 50001','','21centuryenviro@gmail.com',2),('3A Management Corp (Frontier Bakery)','Rajinder Thind','8851 Keele Street\n\n, Concord ON ,L4K2N1',' 905-738-0300 x 1225\n','','rajinder.thind@3amanagementcorp.com',3);
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-17 11:15:42