package org.dspace.content.integration.crosswalks;

import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.dspace.content.Metadatum;
import org.dspace.content.Item;

/**
 * Implementazione del virtual field processing per effettuare l'escape xml dei
 * caratteri speciali
 * 
 * @author bollini
 */
public class VirtualFieldXMLEscape implements VirtualFieldDisseminator,
		VirtualFieldIngester {
	public String[] getMetadata(Item item, Map<String, String> fieldCache,
			String fieldName) {
		// Check to see if the virtual field is already in the cache
		// - processing is quite intensive, so we generate all the values on
		// first request
		if (fieldCache.containsKey(fieldName))
			return fieldCache.get(fieldName).split("\\|");
		
		String[] virtualFieldName = fieldName.split("\\.", 3);
		Metadatum[] dcvs = item.getMetadataByMetadataString(virtualFieldName[2]);
		StringBuffer out = null;
		if (dcvs != null && dcvs.length > 0) {
			out = new StringBuffer();
			for (Metadatum dc : dcvs)
			{
				out.append(StringEscapeUtils.escapeXml(dc.value)).append("|");
			}
			if (out != null && out.length() > 0) {
				String result = out.substring(0, out.length() - 1);
				fieldCache.put(fieldName, result);
				return result.split("\\|");
			}
		}
		return null;
	}

	public boolean addMetadata(Item item, Map<String, String> fieldCache,
			String fieldName, String value) {
		// NOOP - we won't add any metadata yet, we'll pick it up when we
		// finalise the item
		return true;
	}

	public boolean finalizeItem(Item item, Map<String, String> fieldCache) {
		return false;
	}
}
