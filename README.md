# SpaceX-Dragon
SIX Coding Exercise


Approach
--------

I wanted to divide responsibilities for different classes inside packages:
 1. **Model** - holds Mission and Rocket as simple objects
 2. **Repository** - manages Mission and Rocket (add/get)
 3. **Service** - provide actions to perform on already existing Missions and Rockets
 4. **Status** - package with statuses for model
 5. **Exception** - package for custom exception (for edge cases) 


Assumptions
-----------

 1. Missions and Rockets are recognized by their names in main Manager.
 2. Abnormal behaviour is handled by Custom Exception (SpaceException);
 3. Possible rocket status changes are limited:
    * ON_GROUND -> IN_SPACE
    * IN_SPACE -> ON_GROUND
    * IN_SPACE -> IN_REPAIR
    * IN_REPAIR -> IN_SPACE
    * For cleaner flow I assumed we cannot move between IN_REPAIR and ON_GROUND status
 4. Rocket status can be changed by specific operation from main Manager - status cannot be changed directly
 5. Mission status can be changed by specific operation from main Manager - status cannot be changed directly