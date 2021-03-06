package in.parapengu.spork.module.modules.region;

import in.parapengu.spork.exception.region.ModuleParsingException;
import in.parapengu.spork.module.builder.BuildPhase;
import in.parapengu.spork.module.builder.BuilderInfo;
import in.parapengu.spork.module.builder.parsers.Parser;
import in.parapengu.spork.module.builder.parsers.ParsingContext;
import in.parapengu.spork.module.modules.region.types.BlockRegion.BlockParser;
import in.parapengu.spork.module.modules.region.types.CuboidRegion.CuboidParser;
import in.parapengu.spork.module.modules.region.types.RectangleRegion.RectangleParser;
import in.parapengu.spork.module.modules.region.types.CylinderRegion.CylinderParser;
import in.parapengu.spork.module.modules.region.types.CircleRegion.CircleParser;
import in.parapengu.spork.module.modules.region.types.SphereRegion.SphereParser;
import in.parapengu.spork.module.modules.region.types.ReferencedRegion.ReferencedParser;
import in.parapengu.spork.module.modules.region.types.combinations.UnionRegion.UnionParser;
import in.parapengu.spork.module.modules.region.types.combinations.NegativeRegion.NegativeParser;
import in.parapengu.spork.module.modules.region.types.combinations.ComplementRegion.ComplementParser;
import in.parapengu.spork.module.modules.region.types.combinations.IntersectRegion.IntersectParser;

import java.util.ArrayList;
import java.util.List;

@BuilderInfo(BuildPhase.LOAD)
public class RegionBuilder extends Parser<RegionModule, RegionParser> {

	private static List<RegionParser> parsers;

	static {
		parsers = new ArrayList<>();
		// Standard Regions
		parsers.add(new BlockParser());
		parsers.add(new CuboidParser());
		parsers.add(new RectangleParser());
		parsers.add(new CylinderParser());
		parsers.add(new CircleParser());
		parsers.add(new SphereParser());
		parsers.add(new ReferencedParser());

		// Combination Regions
		parsers.add(new UnionParser());
		parsers.add(new NegativeParser());
		parsers.add(new ComplementParser());
		parsers.add(new IntersectParser());
	}

	public RegionBuilder() {
		super("regions", parsers, RegionModule.class);
	}

	@Override
	public List<RegionModule> add(RegionParser parser, ParsingContext context, List<RegionModule> modules) throws ModuleParsingException {
		context.addRegions(modules);
		return super.add(parser, context, modules);
	}

}
