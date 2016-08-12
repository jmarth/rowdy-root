
CREATE TABLE "distance_visions" (
`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
`vid`	INTEGER NOT NULL,
`DVODSC`	TEXT,
`DVOSSC`	TEXT,
`DVODCC`	TEXT,
`DVOSCC`	TEXT
)

CREATE TABLE "glasses_rxs" (
`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
`vid`	INTEGER NOT NULL,
`OD_Sphere`	REAL,
`OD_Cyl`	REAL,
`OD_Axis`	REAL,
`OD_Add`	REAL,
`OS_Sphere`	REAL,
`OS_Cyl`	REAL,
`OS_Axis`	REAL,
`OS_Add`	REAL,
`GlassesRxNotes`	TEXT
)

CREATE TABLE "refractions" (
`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
`vid`	INTEGER NOT NULL,
`isManifest`	INTEGER,
`SC_OD_Sphere`	REAL,
`SC_OD_Cyl`	REAL,
`SC_OD_Axis`	REAL,
`SC_OS_Sphere`	REAL,
`SC_OS_Cyl`	REAL,
`SC_OS_Axis`	REAL,
`CC_OD_Sphere`	REAL,
`CC_OD_Cyl`	REAL,
`CC_OD_Axis`	REAL,
`CC_OS_Sphere`	REAL,
`CC_OS_Cyl`	REAL,
`CC_OS_Axis`	REAL
)

CREATE TABLE "pupils" (
`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
`vid`	INTEGER NOT NULL,
`isBothPupilsNormal`	INTEGER,
`bothShape`	TEXT,
`bothDiameter`	INTEGER,
`isBothRAPD`	INTEGER,
`isBothSynechia`	INTEGER,
`isRightPupilNormal`	INTEGER,
`rightShape`	TEXT,
`rightDiameter`	INTEGER,
`isRightRAPD`	INTEGER,
`isRightSynechia`	INTEGER,
`isLeftPupilNormal`	INTEGER,
`leftShape`	TEXT,
`leftDiameter`	INTEGER,
`isLeftRAPD`	INTEGER,
`isLeftSynechia`	INTEGER
)

CREATE TABLE "anterior_chambers" (
`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
`vid`	INTEGER NOT NULL,
`isACODNormal`	INTEGER,
`isACOSNormal`	INTEGER,
`ACDepthOD`	INTEGER,
`ACDepthOS`	INTEGER,
`ACAngleOD`	TEXT,
`ACAngleOS`	TEXT,
`PASOD`	TEXT,
`PASOS`	TEXT,
`ACODKP`	INTEGER,
`ACOSKP`	INTEGER,
`isShuntOD`	INTEGER,
`isScarringOD`	INTEGER,
`isTraumaOD`	INTEGER,
`isBlebOD`	INTEGER,
`isShuntOS`	INTEGER,
`isScarringOS`	INTEGER,
`isTraumaOS`	INTEGER,
`isBlebOS`	INTEGER,
`isVascularOD`	INTEGER,
`BlebOD_Num`	INTEGER,
`isVascularOS`	INTEGER,
`BlebOS_Num`	INTEGER,
`isKSpindleOD`	INTEGER,
`isKSpindleOS`	INTEGER
)

