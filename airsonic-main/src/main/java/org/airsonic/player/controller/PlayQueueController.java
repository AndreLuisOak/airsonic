/*
 * Classe refatorada para aplicar o padrão Strategy indiretamente através do PlayerQueueService.
 *
 * Antes:
 *   - O controller montava todo o mapa de atributos e devolvia o ModelAndView.
 * Depois:
 *   - O PlayerQueueService concentra a lógica de criação do modelo e escolha da estratégia.
 *   - O controller apenas coleta as informações do request e delega a construção do resultado.
 */

package org.airsonic.player.controller;

import org.airsonic.player.domain.Player;
import org.airsonic.player.domain.User;
import org.airsonic.player.domain.UserSettings;
import org.airsonic.player.service.PlayerService;
import org.airsonic.player.service.SecurityService;
import org.airsonic.player.service.SettingsService;
import org.airsonic.player.service.PlayQueueService;  // <- novo serviço que implementa Strategy internamente

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controlador responsável por exibir a fila de reprodução (play queue).
 * Agora ele apenas coordena a chamada para o serviço adequado.
 */
@Controller
@RequestMapping("/playQueue")
public class PlayQueueController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private SettingsService settingsService;

    // Novo serviço responsável por aplicar a estratégia correta de montagem do modelo
    @Autowired
    private PlayQueueService playQueueService;

    /**
     * Endpoint principal responsável por montar a tela da fila de reprodução.
     */
    @GetMapping
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // Recupera o usuário autenticado
        User user = securityService.getCurrentUser(request);

        // Recupera as configurações do usuário (visibilidade, modo festa, notificações etc.)
        UserSettings userSettings = settingsService.getUserSettings(user.getUsername());

        // Identifica o player atual (pode representar um navegador, app móvel, etc.)
        Player player = playerService.getPlayer(request, response);

        /*
         * Aqui acontece a mudança principal:
         * Em vez de o controller montar o modelo manualmente,
         * ele delega ao serviço PlayQueueService.
         * 
         * Esse serviço é quem aplica o padrão Strategy internamente —
         * escolhendo automaticamente a lógica mais adequada
         * (por exemplo: modo padrão, modo festa, modo colaborativo...).
         */
        return playQueueService.buildPlayQueueModel(user, player, userSettings);
    }
}
