package com.zmtech.zentity;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ZEntityDataLoader {

    /** Location of the data file to load. Can be called multiple times to load multiple files.
     * @return Reference to this for convenience.
     */
    ZEntityDataLoader location(String location);
    /** List of locations of files to load. Will be added to running list, so can be called multiple times and along
     * with the location() method too.
     * @return Reference to this for convenience.
     */
    ZEntityDataLoader locationList(List<String> locationList);

    /** String with XML text in it, ready to be parsed.
     * @return Reference to this for convenience.
     */
    ZEntityDataLoader xmlText(String xmlText);
    ZEntityDataLoader csvText(String csvText);
    ZEntityDataLoader jsonText(String jsonText);

    /** A Set of data types to match against the candidate files from the component data directories and the
     * entity-facade.load-data elements.
     * @return Reference to this for convenience.
     */
    ZEntityDataLoader dataTypes(Set<String> dataTypes);
    /** Used along with dataTypes; a list of component names to load data from. If none specified will load from all components. */
    ZEntityDataLoader componentNameList(List<String> componentNames);

    /** The transaction timeout for this data load in seconds. Defaults to 3600 which is 1 hour.
     * @return Reference to this for convenience.
     */
    ZEntityDataLoader transactionTimeout(int tt);

    /** If true instead of doing a query for each value from the file it will just try to insert it and if it fails then
     * it will try to update the existing record. Good for situations where most of the values will be new in the db.
     * @return Reference to this for convenience.
     */
    ZEntityDataLoader useTryInsert(boolean useTryInsert);

    /** If true will check all foreign key relationships for each value and if any of them are missing create a new
     * record with primary key only to avoid foreign key constraint errors.
     *
     * This should only be used when you are confident that the rest of the data for these new fk records will be loaded
     * from somewhere else to avoid having orphaned records.
     *
     * @return Reference to this for convenience.
     */
    ZEntityDataLoader dummyFks(boolean dummyFks);

    /** Set to true to disable Entity Facade ECA rules (for this import only, does not affect other things happening
     * in the system).
     * @return Reference to this for convenience.
     */
    ZEntityDataLoader disableEntityEca(boolean disable);
    ZEntityDataLoader disableAuditLog(boolean disable);
    ZEntityDataLoader disableFkCreate(boolean disable);
    ZEntityDataLoader disableDataFeed(boolean disable);

    ZEntityDataLoader csvDelimiter(char delimiter);
    ZEntityDataLoader csvCommentStart(char commentStart);
    ZEntityDataLoader csvQuoteChar(char quoteChar);

    /** For CSV files use this name (entity or service name) instead of looking for it on line one in the file */
    ZEntityDataLoader csvEntityName(String entityName);
    /** For CSV files use these field names instead of looking for them on line two in the file */
    ZEntityDataLoader csvFieldNames(List<String> fieldNames);
    /** Default values for all records to load, if applicable for given entity or service */
    ZEntityDataLoader defaultValues(Map<String, Object> defaultValues);

    /** Only check the data against matching records in the database. Report on records that don't exist in the database
     * and any differences with records that have matching primary keys.
     *
     * @return List of messages about each comparison between data in the file(s) and data in the database.
     */
    List<String> check();
    long check(List<String> messageList);

    /** Load the values into the database(s). */
    long load();

    /** Create an EntityList with all of the values from the data file(s).
     *
     * @return EntityList representing a List of EntityValue objects for the values in the XML document(s).
     */
//    EntityList list();
}
