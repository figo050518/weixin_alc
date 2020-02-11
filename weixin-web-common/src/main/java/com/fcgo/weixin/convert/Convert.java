package com.fcgo.weixin.convert;

import java.util.List;

public interface Convert<DOMAIN,CLIENT> {
    
    public CLIENT convertToDTO(DOMAIN domain);

    public DOMAIN convertToDomain(CLIENT client);


    public List<CLIENT> convertCollectionToDTO(List<DOMAIN> domains);


    public List<DOMAIN> convertCollectionToDomain(List<CLIENT> clients);
}
