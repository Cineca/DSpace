/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */
package org.dspace.discovery;

import org.dspace.content.*;
import org.dspace.content.Collection;
import org.dspace.discovery.configuration.DiscoveryConfiguration;
import org.dspace.discovery.configuration.DiscoveryConfigurationService;
import org.dspace.kernel.ServiceManager;
import org.dspace.utils.DSpace;

import java.sql.SQLException;
import java.util.*;

/**
 * Util methods used by discovery
 *
 * @author Kevin Van de Velde (kevin at atmire dot com)
 * @author Mark Diggory (markd at atmire dot com)
 * @author Ben Bosman (ben at atmire dot com)
 */
public class SearchUtils {
    /** Cached search service **/
    private static SearchService searchService;


    public static SearchService getSearchService()
    {
        if(searchService ==  null){
            DSpace dspace = new DSpace();
            org.dspace.kernel.ServiceManager manager = dspace.getServiceManager() ;
            searchService = manager.getServiceByName(SearchService.class.getName(),SearchService.class);
        }
        return searchService;
    }

    public static DiscoveryConfiguration getDiscoveryConfiguration(){
        return getDiscoveryConfiguration(null);
    }

    public static DiscoveryConfiguration getDiscoveryConfiguration(DSpaceObject dso){
        return getDiscoveryConfigurationByName(dso!=null?dso.getHandle():null);
    }

    public static DiscoveryConfiguration getDiscoveryConfigurationByName(
            String configurationName)
    {
        DiscoveryConfigurationService configurationService = getConfigurationService();

        DiscoveryConfiguration result = null;
        if(configurationName == null){
            result = configurationService.getMap().get("site");
        }else{
            result = configurationService.getMap().get(configurationName);
        }

        if(result == null){
            //No specific configuration, get the default one
            result = configurationService.getMap().get("default");
        }

        return result;
    }

    public static DiscoveryConfigurationService getConfigurationService() {
        DSpace dspace  = new DSpace();
        ServiceManager manager = dspace.getServiceManager();
        return manager.getServiceByName(DiscoveryConfigurationService.class.getName(), DiscoveryConfigurationService.class);
    }

    /**
     * Method that retrieves a list of all the configuration objects from the given item
     * A configuration object can be returned for each parent community/collection
     * @param item the DSpace item
     * @return a list of configuration objects
     */
    public static List<DiscoveryConfiguration> getAllDiscoveryConfigurations(Item item) throws SQLException {
        Map<String, DiscoveryConfiguration> result = new HashMap<String, DiscoveryConfiguration>();

        Collection[] collections = item.getCollections();
        for (Collection collection : collections) {
            DiscoveryConfiguration configuration = getDiscoveryConfiguration(collection);
            if(!result.containsKey(configuration.getId())){
                result.put(configuration.getId(), configuration);
            }
        }

        //Also add one for the default
        DiscoveryConfiguration configuration = getDiscoveryConfiguration(null);
        if(!result.containsKey(configuration.getId())){
            result.put(configuration.getId(), configuration);
        }

        return Arrays.asList(result.values().toArray(new DiscoveryConfiguration[result.size()]));
    }

}
