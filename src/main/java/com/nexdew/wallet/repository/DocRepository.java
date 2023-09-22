package com.nexdew.wallet.repository;

import com.nexdew.wallet.common.enums.DocumentType;
import com.nexdew.wallet.entity.Documents;
import java.util.Optional;



public interface DocRepository extends BaseRepository<Documents,Long>{


    Optional<Documents> findBypath(String path);
    Optional<Documents> findByUserIdAndDocumentType(long userId,DocumentType documentType);
    Optional<Documents> findByUser_Id(int userID);
}
