package dev.milzipmoza.review.annotation

import org.springframework.stereotype.Component

/**
 * 엔터프라이즈 아키텍처 패턴 (FOWLER, 2015) 서비스 계층(Service Layer) 의미합니다.
 * 해당 어노테이션은 어플리케이션 영역에서만 사용이 가능합니다.
 * 해당 어노테이션을 가지는 클래스에서는 캡슐화된 도메인 로직을 호출하는 역할을 합니다.
 * 해당 어노테이션을 가지는 클래스에서는 비즈니스 로직을 도메인 로직으로 구성하는 역할을 합니다.
 *
 * 해당 어노테이션은 스프링에서의 Service 어노테이션의 동의할 수 없는 설명을 바로잡기 위해 정의하였습니다.
 *
 * Service 어노테이션 주석에 따르면 아래와 같습니다.
 *
 * Indicates that an annotated class is a "Service", originally defined by Domain-Driven
 * Design (Evans, 2003) as "an operation offered as an interface that stands alone in the
 * model, with no encapsulated state."
 *
 * <p>May also indicate that a class is a "Business Service Facade" (in the Core J2EE
 * patterns sense), or something similar. This annotation is a general-purpose stereotype
 * and individual teams may narrow their semantics and use as appropriate.
 *
 * DDD 의 서비스를 의미하기도, 비즈니스 서비스 파사드를 의미하기도 한다고 하는데 후자의 설명은 동의하지만 전자의 설명은 동의할 수 없어 정의하였습니다.
 * DDD 의 서비스는 도메인 영역의 서비스이며, 이 서비스는 어떤 기술도 침투하지 않은 순수한 영역으로 보존되어야 합니다.
 *
 * @see org.springframework.stereotype.Service
 */
@kotlin.annotation.Target(AnnotationTarget.CLASS)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Component
annotation class ApplicationService(val value: String = "")
