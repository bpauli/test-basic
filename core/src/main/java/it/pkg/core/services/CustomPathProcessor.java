package it.pkg.core.services;

import org.apache.sling.api.SlingHttpServletRequest;
import org.jetbrains.annotations.NotNull;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.adobe.cq.wcm.core.components.services.link.PathProcessor;


@Component(property = Constants.SERVICE_RANKING + ":Integer=" + (Integer.MIN_VALUE + 1),
           service = PathProcessor.class)
public class CustomPathProcessor implements PathProcessor {

    @Reference(target = "(component.name=com.adobe.cq.wcm.core.components.internal.link.DefaultPathProcessor)")
    private PathProcessor delegatePathProcessor;

    @Override
    public boolean canHandle(String s, SlingHttpServletRequest slingHttpServletRequest) {
        return true;
    }

    @Override
    public @NotNull String fixPath(String s, SlingHttpServletRequest slingHttpServletRequest) {
        return delegatePathProcessor.fixPath(s, slingHttpServletRequest);
    }

    @Override
    public @NotNull String mapPath(String s, SlingHttpServletRequest slingHttpServletRequest) {
        return delegatePathProcessor.mapPath(s, slingHttpServletRequest);
    }

    @Override
    public @NotNull String externalizeLink(@NotNull String s, @NotNull SlingHttpServletRequest slingHttpServletRequest) {
        // do some custom stuff
        return "http://www.example.com" + s;
    }
}
