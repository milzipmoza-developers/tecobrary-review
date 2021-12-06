package dev.milzipmoza.review.mongo.mark.mongo

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MongoMarkedRepository : MongoRepository<DocumentMarked, ObjectId>