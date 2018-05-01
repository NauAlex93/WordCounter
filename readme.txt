Delphi 6
Release Notes


============================================================

This file contains important supplementary and late-breaking
information that may not appear in the main product
documentation. We recommend that you read this file in its
entirety.

For information on new features in this release, choose
"What's New" in the online Help contents.


============================================================
CONTENTS

* OTHER RELEASE NOTES INCLUDED WITH THIS PRODUCT
* ABOUT EDITING THE REGISTRY
* NOTES AND ISSUES: GENERAL
* NOTES AND ISSUES: CLX
* NOTES AND ISSUES:
  WEB/DISTRIBUTED APPLICATION DEVELOPMENT
* NOTES AND ISSUES: DATABASE DEVELOPMENT
* NOTES AND ISSUES: INTERNATIONAL/LOCALES
* NOTES AND ISSUES: VISIBROKER/CORBA
* NOTES AND ISSUES: ONLINE HELP
* PRODUCT INFORMATION ON THE WEB


============================================================
OTHER RELEASE NOTES INCLUDED WITH THIS PRODUCT

* INSTALL contains system requirements and product
  installation information.

* DEPLOY contains information about redistributing your
  applications.

* LICENSE contains information on licensing allowances and
  limitations for this product and other Borland software
  that is bundled with it.

 The three files listed above, along with this file, are
 installed to your main product directory (default:
 C:\Program Files\Borland\Delphi6).

 You can also find detailed information about new features
 and compatibility issues in DEL6NEW.HLP, which is part of
 the main online Help system (look for "What's New" in the
 Help contents or index).

* BDEREADME, BDEINST and BDEDEPLOY contain release notes,
  installation, and deployment information about the
  Borland Database Engine (BDE) and Borland SQL Links
  products. These files are located in your main BDE
  directory (installation default: c:\Program Files\Common
  Files\Borland Shared\Bde).

 IMPORTANT: Any BDE-related information in this README
 overrides any corresponding information provided in the
 BDE release notes.

 Additional notes about existing issues with BDE and
 utility applications supplied with BDE are covered in the
 KNOWN ISSUES section later in this document.


============================================================
ABOUT EDITING THE WINDOWS REGISTRY

Warning:
 Registry edits are performed at your own risk.

Several items in this document and in other parts of the
documentation that accompany this product describe how to
edit your Windows registry. In most cases, we recommend that
you use the Microsoft Regedit tool to perform your editing.
Whatever tool you use, however, you should, before making
any edits, make backups of your registry and thoroughly
familiarize yourself with the editing tool and the registry
editing process. The registry is a critical component of
your operating system, and any mistakes made while editing
it can cause your machine to stop functioning.


============================================================
NOTES AND ISSUES:
GENERAL

Compatibility issues
------------------------------

* Design-time packages that used ToolsAPI now also require
  designide.

* Comma-delimited string handling

 When assigning comma-delimited values to a TStringList
 using the CommaText property, a trailing comma in the
 source string will result in a blank item in the
 StringList. For example, if CommaText is set to:

 "String1, String2, String3,"

 The StringList will contain:

 String1
 String2
 String3
 <Blank>

 This is a change in behavior from previous releases.

Other compatibility issues between Delphi 6 and earlier
versions of Delphi, as well as between Delphi 6 and other
products, are described in the What's New section of your
online Help system (search for "Compatibility" in the Help
Index).

Common controls library version note
------------------------------

Outdated versions of the Windows common controls library
(COMCTL32.DLL) can result in various graphical and
behavioral problems when using Delphi and any programs you
develop with Delphi. To help you avoid such problems, Delphi
provides a separate (and redistributable) common controls
library updater. Running the updater will refresh an
outdated common controls library, but will not replace later
versions of the library that may have been installed on your
system by other products released after Delphi 6. Before
running the updater, be sure to read the readme.txt and
license.txt files that accompany it. And if you redistribute
the updater to your customers, be sure to include the readme
and license files in the deployment.

The common controls updater can be run directly from your CD
at:
\info\extras\comctl\50comupd.exe

Note:
 You can also keep your common controls library current by
 installing Internet Explorer 5.5 or later. If you'd rather
 install the full browser than the common controls updater,
 run
     \ie55\i386\ie5setup.exe

Environment options/environment variable usage
------------------------------

You can now use operating-system environment variables to
specify directory paths in certain IDE dialogs. Use of
environment variables is available on the
Directories/Conditionals page of the Project Options dialog
and on the Library page of the Environment Options dialog.
The syntax is $(VariableName). For example, under Search
Path, you can now enter

$(TEMP);$(DELPHI)\source

The $(DELPHI) macro, used to specify directory paths, still
points by default to the directory immediately above
Delphi's bin directory. But you can override this by
creating an environment variable called DELPHI.

The Environment Options dialog now has an Environment
Variables page that you can use to set OS environment
variables.

Working with the sample imported automation
server wrappers for MS Office
------------------------------

Delphi installs a package with pre-imported wrapper
components for either Office 97 or Office 2000. If you need
to change the sample automation server packages after
installation:

1. Choose Component | Install Packages and remove the
   current server package. The package descriptions are:
* Microsoft Office 2000 Sample Automation Server Wrapper
  Components
* Microsoft Office 97 Sample Automation Server Wrapper
  Components

2. Add the other automation server package by clicking the
   Add button and browsing to your \delphi\bin directory.
   The package names are:
* dcloffice2k60.bpl (for Office 2000)
* dclaxserver60.bpl (for Office 97)

Note that the Office automation server demos found under
\Demos\ActiveX are written for Office 97 but can be compiled
for Office 2000 with slight modifications.

ActiveX Wizard/licensing issue
------------------------------

[Enterprise, Professional editions only]
Delphi does not directly support the licensing of ActiveX
controls for Web deployment. License package (.LPK) files,
which are required for runtime licensing of ActiveX
controls, can only be generated by LPK_TOOL.EXE, a utility
available in Microsoft's Internet SDK. For more information
on the license-creation tool and how it is used, visit
http://support.microsoft.com and search for "LPK".


============================================================
NOTES AND ISSUES:
CLX

About the CLX libraries
------------------------------

Delphi 6 includes the new CLX libraries for cross-platform
development. CLX maintains the architecture and class
hierarchy of the original VCL for Windows. The most obvious
difference is that the VisualCLX GUI components call the Qt
libraries for drawing graphics--which run on both Linux and
Windows--instead of the Windows API.

CLX source can be found in /Delphi6/Source/VCL/CLX. The
compiled versions are in your /Delphi6/Lib directory.

The names of CLX Units that link with Qt (VisualCLX GUI
units) now begin with "Q" and the locations of some classes
are different in CLX. TWidgetControl replaces the VCL's
TWinControl; other components (e.g. TScrollingWidget) have
corresponding names. The type declaration

     TWinControl = TWidgetControl;

appears in QControls to simplify sharing of source code.

Message loops and events work differently in CLX
applications, but this will primarily affect component
writing. Most component and property editors will port
easily. TObject.Dispatch and message method syntax on
classes still work; in CLX Applications, however, OS
notifications are not sent through Windows-style messaging.

To create applications that are easily portable between
Windows and Linux, remember to
* reduce or IFDEF WinAPI calls.
* eliminate Windows messaging (PostMessage, SendMessage)
  within an application.
* use TIniFile instead of TRegIniFile.
* observe case-sensitivity in file and directory names.


============================================================
NOTES AND ISSUES:
WEB AND DISTRIBUTED APPLICATION
DEVELOPMENT

MSXML.DLL required for SOAP, WSDL, and XMLMapper
------------------------------

The MSXML.DLL library must be present and registered in
order to use the new SOAP, WSDL, and XMLMapper features
included with this product. If the library is not present on
your machine, you can obtain it as part of an XML Parser
package from the Microsoft Download Center at:

http://www.microsoft.com/downloads/search.asp?

Choose the "Keyword Search" option, then search for "MSXML".

To register the library from a command prompt, cd to the
location where the MSXML.DLL file is installed (e.g.
\winnt\system32), then type

REGSVR32 MSXML.DLL

WebServices notes
------------------------------

* The THTTPRIO component used for client SOAP connections
  can be lifetime-managed as either a component or as an
  interfaced object. It is managed as a component when its
  owner is non-nil, such as when it is placed on a form or
  on a data module. In such cases, be sure to nil out any
  interface references that are not local variables before
  the owner is destroyed.

 For example:

 TForm1 = class(Tform)
     HTTPRIO1:THTTPRIO;
 . . .
 private
     MyInterface:IMyInterface;
 . . .
 end;

 TForm1.SomeMethod;
 begin
     MyInterface:=HTTPRIO1 as IMyInterface;
 end;

 TForm.FormDestroy(Sender: TObject);
 begin
     MyInterface:=nil;
 end;

Using the Web App Debugger with Delphi 5 projects
------------------------------

To run Delphi 5 Web Server Applications using the Delphi 6
Web App Debugger, it is necessary to create a new Web Server
Application, selecting "Web App Debugger executable" as the
server type. Then remove the default WebModule and add your
existing WebModules to the new application. For each
WebModule you add, add the following code to the unit's
initialization section:

if WebRequestHandler <> nil then
 WebRequestHandler.WebModuleClass := TWebModule2;

Note that "TWebModule2" should be replaced with the name of
the actual WebModule.

WebSnap notes
------------------------------

* Environment options:

 The Internet tab is used to set some WebSnap configuration
 options.

 An external HTML editor must be specified in order to
 enable the HTML editor command in the Internet toolbar.

* InterBase 6 implementation note

 If you are using a WebSnap application with InterBase 6, a
 local database connection is not recommended. Instead, use
 a client/server connection specified by a protocol-
 specific prefix to the path to the database.

 Examples:

 Local connection (not recommended):
 C:\PathTo\Database\DBName.gdb

 TCP/IP connection on Windows:
 saturn:C:\PathTo\Database\DBName.gdb

 TCP/IP connection on UNIX:
 jupiter:/usr/PathTo/Database/DBName.gdb

 NetBEUI connection:
 \\venus\C:\PathTo\Database\DBName.gdb

 IPX/SPX connection:
 mars@vol2:\PathTo\Database\DBName.gdb

 If the WebSnap application is on the same machine as the
 server, you can use localhost:
 localhost:C:\PathTo\Database\DBName.gdb


============================================================
NOTES AND ISSUES:
DATABASE DEVELOPMENT

DB2 notes for dbExpress
------------------------------

* The provided DB2 driver is certified for DB2 version 6 and
  7. The client version and server version must be the
  same. BDE also supports both DB2 versions.

Oracle notes for dbExpress
------------------------------

* The Oracle driver provided for dbExpress components is
  certified for Oracle 8.1.6. BDE and ADO also support
  Oracle 8.1.5. In all cases, the client version and server
  version must be the same.

* When qualifying a table name with a user name, you must
  use quotes around the table name when CommandType is set
  to ctTable.

Static linking DataSnap clients
------------------------------

It is now possible to eliminate the need to deploy the
TClientDataSet runtime module (MIDAS.DLL). This can be done
for DataSnap clients and other applications requiring this
module by including MidasLib in the uses statement of any
unit in the project. DataSnap servers still require
MIDAS.DLL.

Static linking of dbExpress drivers
------------------------------

The dbExpress driver DLLs can be linked directly into an
application by including the appropriate unit in the uses
clause of the application or any unit:

* dbexpint (InterBase)
* dbexpORA (Oracle)
* dbexpdb2 (DB2)
* dbexpmys (MySQL)

Only one dbExpress driver can be linked into each client
application.

InterBase 6 Dialect 3
------------------------------

To use the new InterBase 6 Dialect 3 features, add an entry
to your Windows registry under
 HKEY_LOCAL_MACHINE\SOFTWARE\
     Borland\Database Engine\Settings
     \Drivers\Intrbase\Db Open\SQLDIALECT
and set the value to "3".

When an InterBase alias is created, the new entry will be
available in the .CFG file.

To use InterBase 5.6, SQLDIALECT can be set to "1" (existing
IB aliases which do not have the SQLDIALECT entry default to
SQLDIALECT=1 or to the registry setting when the SQLDIALECT
entry is added to the registry).

Other database development notes
------------------------------

* Delphi 6 supports both InterBase 5.6 and 6.0. The client
  version and server version must be the same.

* Delphi 6 installs sample database files that are
  compatible with the provided InterBase 6.0.1. Sample
  databases which are compatible with older versions of
  InterBase are on your CD in the Info\Extras directory.

* Enterprise edition only: Microsoft Data Access Components
  (MDAC) components are available for separate installation
  by running MDAC_TYP.EXE from the \MDAC directory on your
  product CD.

* A VCL hierarchy change involving TClientDataSet and
  TCustomClientDataSet causes incompatible parameter lists
  and affects the behavior of a number of types and events.
  For details, see What's New Help and search the index for
  the phrase "compatibility".

* The MySQL driver has been tested with MySQL version
  3.22.32.

* The Borland Database Engine CAB file (BDEINST.CAB) is no
  longer digitally signed. This CAB file is provided for
  backward compatibility. For new applications, we
  recommend deploying the BDE with the BDE merge modules.


============================================================
NOTES AND ISSUES:
INTERNATIONAL/LOCALES

Menu Shortcut Conflicts
------------------------------

Menu shortcuts in the IDE may conflict with system shortcuts
and international character key combinations. If you want to
disable the Ctrl+Alt hot keys in the IDE, set this DWORD
registry key:

 HKEY_CURRENT_USER\Software\Borland\Delphi\6.0\
     Editor\Options\NoCtrlAltKeys

to "1".

Resource DLL Wizard and Windows 2000 locales
------------------------------

The Resource DLL wizard requires a distinct two- or three-
letter file extension for each translated version of a
project it maintains. For some languages that are written in
both Latin and Cyrillic characters, however, Windows 2000
uses the same default extension regardless of the character
set (as shown in the table below). If you want to maintain
both Latin and Cyrillic versions for such languages, change
the extension for one of the translations when you run the
Resource DLL wizard.

Language          Locale ID   Extension
                              
Azeri (Cyrillic)  82C         AZE
Azeri (Latin)     42C         AZE
                              
Uzbek (Cyrillic)  843         UZB
Uzbek (Latin)     443         UZB


============================================================
NOTES AND ISSUES:
VISIBROKER/CORBA

VisiBroker 4, CORBA, and IDL-TO-PAS
------------------------------

VisiBroker 4 lets you create CORBA clients and servers that
can interoperate with other CORBA applications written in
C++ and Java. The product includes an IDL-to-Pascal compiler
capable of compiling CORBA IDL into Delphi Pascal stub and
skeleton code, and the associated runtime library required
to interface between this code and the VisiBroker 4 C++ ORB.
This implementation supports simple and complex data types
in IDL, along with callbacks, the COSNaming Service and the
COSEvent Service.

Many examples are provided to illustrate the different
features of the VisiBroker 4 for Delphi implementation.
These examples are located in your
Delphi6/Demos/Corba/IDL2PAS directory.

IDL-to-Delphi Pascal Language Mapping is specified in
chapter two of the VisiBroker 4 for Delphi manual (installed
to your Delphi6/Doc/IDL2PAS directory). The manual is in PDF
format; a viewer is also provided.

CORBA compatibility notes
------------------------------

The TypeLib interface and CorbaConnection component are only
compatible with the VisiBroker 3.3 ORB. The Corba Wizards,
located on the CORBA tab of the File | New | Other tab of
the projects dialog box, are compatible with both VisiBroker
3.3 and VisiBroker 4.1.


============================================================
NOTES AND ISSUES:
ONLINE HELP

How to disengage CLX Help Reference linking
------------------------------
[Applies to Enterprise and Professional editions only]

The default Help installation for Delphi 6 includes
documentation for two component libraries: the familiar
Visual Component Library (VCL) for Windows development, and
the new CLX library documentation for cross-platform
development. As a result, you'll often be presented with a
choice of two titles when seeking information on a specific
object or property--one title from the VCL reference, and
the other from the CLX reference. If, however, you are doing
Windows platform development only and you'd like to forego
the library choice when summoning Help, you can easily
disengage the CLX reference from the main Help system's
linking mechanisms. Here's how:

1. If Delphi Help is running, close it (no need to close
   the Delphi IDE, though).

2. Using any text editor, open the files DELPHI6.OHL,
   DELPHI6.OHI, and DELPHI6.OHC (all are located in your
   Delphi\Help directory).

3. In each file, comment out each line containing the
   string "DLX1CLX" by entering a semicolon as the first
   character on the line.

4. Save each file.

5. In your Delphi\Help folder, delete any hidden files with
   the extension "GID". Also delete any files with an
   extension beginning with "F".

Note:
 All of these files are automatically generated by Help,
 and will be restored the next time Delphi Help is run. The
 GID is a General Index file, and the "F" files are
 generated when you run a full-text search from the Help
 Topics dialog. Deleting these files--always recommended
 when you alter the Help system configuration or install
 updated Help files--will assure proper indexing and
 database searching when you restart Help.

6. Restart Help, either by running DELPHI6.HLP directly
   from your Delphi\Help folder, or by starting it from the
   IDE using the Help menu or F1. The DLX1CLX.HLP topics
   should now be excluded from your searches and indexes.

If you also delete the DLX1CLX files from your Delphi\Help
folder and later decide to restore them (from CD), remember
to uncomment the DLX1CLX lines in your *.OH? files to allow
the files to be linked back into your larger Help system.

You can also use the DELPHI6.OH* file set to disengage any
other part of the Help system (apart from the core DELPHI6.*
set), or add your own Help files to the system.

Windows Help index limitation on Windows 98/ME
------------------------------

The Windows Help engine on Windows 95, 98, and Millennium
uses a 16-bit list box in the Help Topics dialog's Index
tab, and can display no more than 32,767 keywords. If this
limitation is exceeded, the Index is blank. If you are using
one of these operating systems, you may note that the Help
Index control file (Delphi6.ohi) has been modified to
exclude a number of Help files from the Index. This
modification does not affect other Help system functionality-
-such as crossfile linking or F1 accessibility--and you can
expand the index or modify it further to include or exclude
any Help file reference by adding or removing semi-colon
comment marks before ":Index" entries. When modifying the
.OHI file, however, you must keep the WinHelp viewer
limitation in mind to avoid the "blank index" problem.

Keeping your Help Index pointers current
------------------------------

Whenever your Delphi Help system is modified or
reconfigured, be sure to delete any hidden *.GID files in
your Delphi6/Help directory before restarting Help.

SHED and MRBC utilities not included
------------------------------

The Help file for the Help Compiler Workshop (HCW) product
states that two utilities--SHED.EXE and MRBC.EXE--are
included with HCW. We do not, however, ship these utilities
with our distribution of HCW. They are included in the full
HCW distribution, available free from the Microsoft Web
site.

Help may include references
to features not available in your edition
------------------------------

Your Help system may include references to features or
functionality that is not available in your edition of
Delphi. For example, the Help system included with the
Personal Edition includes topics that describe cross-
platform development and WebSnap features. These sections of
the Help system may be ignored. If you'd like more
information about such features and the advantages of
upgrading to a version of Delphi that includes them,
however, check the Delphi home site at
http://www.borland.com/delphi/.


============================================================
PRODUCT INFORMATION ON THE WEB

In addition to numerous private sites that offer
information, instruction, and samples, the following Borland
Web-based resources provide a continuous stream of news,
product information, updates, code, and other materials. You
can connect to many of these resources directly from the
Help menu.

Delphi Direct [Help|Delphi Direct]
------------------------------

This IDE add-in resource provides an automatically updated
list of links to the latest news, downloads, and other
information about Delphi and Borland.

Code Central
------------------------------

The CodeCentral Repository is a free, searchable database of
code, tips, and other materials of interest to developers.
For details and registration information, visit
http://community.borland.com/codecentral

The Borland Web site
------------------------------

Borland Software Corporation home page:
  http://www.borland.com
  [IDE: Help|Borland Home Page]

Delphi home page:
  http://www.borland.com/delphi/
  [IDE: Help|Delphi Home Page]

Delphi developer support:
  http://www.borland.com/devsupport/delphi/
  [IDE: Help|Delphi Developer Support]

Delphi updates and other downloads:
  http://www.borland.com/devsupport/delphi/downloads/

Delphi documentation site:
  http://www.borland.com/techpubs/delphi

Newsgroups:
  http://www.borland.com/newsgroups/

Worldwide offices and distributors:
  http://www.borland.com/bww/

FTP site (anonymous access):
  ftp.borland.com


============================================================
Copyright © 2001 Borland Software Corporation.
All rights reserved.

