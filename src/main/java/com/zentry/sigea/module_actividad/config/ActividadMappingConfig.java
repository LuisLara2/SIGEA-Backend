package com.zentry.sigea.module_actividad.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zentry.sigea.module_actividad.core.entities.EstadoActividadDomainEntity;
import com.zentry.sigea.module_actividad.core.entities.TipoActividadDomainEntity;
import com.zentry.sigea.module_actividad.core.repositories.IEstadoActividadRepository;
import com.zentry.sigea.module_actividad.core.repositories.ITipoActividadRepository;
import com.zentry.sigea.module_actividad.presentation.models.requestDTO.ActividadRequest;
import com.zentry.sigea.module_actividad.presentation.models.requestDTO.CrearActividadRequest;

/**
 * Configuración de beans para la conversión de IDs a objetos de dominio
 * Permite convertir los IDs recibidos del frontend a objetos completos
 */
@Configuration
public class ActividadMappingConfig {

    private final IEstadoActividadRepository estadoActividadRepository;
    private final ITipoActividadRepository tipoActividadRepository;

    public ActividadMappingConfig(IEstadoActividadRepository estadoActividadRepository,
                                ITipoActividadRepository tipoActividadRepository) {
        this.estadoActividadRepository = estadoActividadRepository;
        this.tipoActividadRepository = tipoActividadRepository;
    }

    /**
     * Bean para convertir CrearActividadRequest (con IDs) a ActividadRequest (con objetos completos)
     */
    @Bean
    public ActividadRequestMapper actividadRequestMapper() {
        return new ActividadRequestMapper();
    }

    /**
     * Clase interna para mapeo de requests
     */
    public class ActividadRequestMapper {

        /**
         * Convierte un CrearActividadRequest (con IDs) a ActividadRequest (con objetos completos)
         * 
         * @param request Request con IDs
         * @return Request con objetos de dominio completos
         * @throws IllegalArgumentException si no se encuentran los objetos por ID
         */
        public ActividadRequest mapToActividadRequest(CrearActividadRequest request) {
            // Validar que los IDs requeridos estén presentes
            validateRequiredIds(request);

            // Obtener los objetos por ID
            EstadoActividadDomainEntity estado = getEstadoActividadById(request.getEstadoId());
            TipoActividadDomainEntity tipoActividad = getTipoActividadById(request.getTipoActividadId());

            // Crear el ActividadRequest con los objetos completos
            return new ActividadRequest(
                request.getTitulo(),
                request.getDescripcion(),
                request.getFechaInicio(),
                request.getFechaFin(),
                estado,
                request.getOrganizadorId(),
                tipoActividad,
                request.getUbicacion()
            );
        }

        /**
         * Obtiene un EstadoActividad por su ID
         */
        public EstadoActividadDomainEntity getEstadoActividadById(String estadoId) {
            if (estadoId == null) {
                throw new IllegalArgumentException("El ID del estado de actividad debe ser un número positivo");
            }

            EstadoActividadDomainEntity estado = estadoActividadRepository.findById(estadoId).orElse(null);
            if (estado == null) {
                throw new IllegalArgumentException(
                    "No se encontró un estado de actividad con ID: " + estadoId
                );
            }

            return estado;
        }

        /**
         * Obtiene un TipoActividad por su ID
         */
        public TipoActividadDomainEntity getTipoActividadById(String tipoActividadId) {
            if (tipoActividadId == null) {
                throw new IllegalArgumentException("El ID del tipo de actividad debe ser un número positivo");
            }

            TipoActividadDomainEntity tipoActividad = tipoActividadRepository.findById(tipoActividadId).orElse(null);
            if (tipoActividad == null) {
                throw new IllegalArgumentException(
                    "No se encontró un tipo de actividad con ID: " + tipoActividadId
                );
            }

            return tipoActividad;
        }

        /**
         * Valida que los IDs requeridos estén presentes
         */
        private void validateRequiredIds(CrearActividadRequest request) {
            if (request.getEstadoId() == null) {
                throw new IllegalArgumentException("El ID del estado de actividad es obligatorio");
            }
            
            if (request.getTipoActividadId() == null) {
                throw new IllegalArgumentException("El ID del tipo de actividad es obligatorio");
            }
            
            if (request.getOrganizadorId() == null) {
                throw new IllegalArgumentException("El ID del organizador es obligatorio");
            }
        }
    }
}